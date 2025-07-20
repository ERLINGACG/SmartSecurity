package com.erling.service.websocket.handler;


import com.erling.lib.opencv.dnn.DnnTest;
import jakarta.annotation.PreDestroy;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Set;
import java.util.concurrent.*;

public class Esp32Stream extends BinaryWebSocketHandler {
    private static final int TCP_PORT = 12345;
    private volatile boolean running = true;
    private final ExecutorService tcpExecutor = Executors.newSingleThreadExecutor(); //tcp启动线程
    private final BlockingQueue<byte[]> imageQueue = new LinkedBlockingQueue<>(50); // 最大缓存50帧
    private final ExecutorService processingExecutor = Executors.newFixedThreadPool(6);

    private final BlockingQueue<byte[]> sendQueue = new LinkedBlockingQueue<>(50);
    // 新增发送线程池
    private final ExecutorService sendExecutor = Executors.newSingleThreadExecutor();



    public Esp32Stream() {
        startTcpServer();
        startProcessingWorker();
        startSendWorker(); // 新增发送线程
    }

    private void startTcpServer() {
        tcpExecutor.execute(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TCP_PORT)) {
                System.out.println("TCP服务器已启动，监听端口: " + TCP_PORT);

                while (running) {
                    Socket clientSocket = serverSocket.accept();
                    handleTcpClient(clientSocket);
                }
            } catch (IOException e) {
                System.err.println("TCP服务器异常: " + e.getMessage());
            }
        });
    }

    private void handleTcpClient(Socket socket) {
        new Thread(() -> {
            try (DataInputStream input = new DataInputStream(socket.getInputStream())) {
                byte[] header = new byte[8];
                while (running) {
                    // 读取完整头信息
                    input.readFully(header);
                    ByteBuffer buffer = ByteBuffer.wrap(header).order(ByteOrder.LITTLE_ENDIAN);
                    int timestamp = buffer.getInt();
                    int imageLength = buffer.getInt();

                    // 分块读取
                    ByteArrayOutputStream baos = new ByteArrayOutputStream(imageLength);
                    byte[] chunk = new byte[1024*10];
                    int remaining = imageLength;

                    while (remaining > 0) {
                        int read = input.read(chunk, 0, Math.min(chunk.length, remaining));
                        if(read <= 0) throw new IOException("流意外结束");
                        baos.write(chunk, 0, read);
                        remaining -= read; // 更新剩余字节数
                    }
                    // 原始数据入队
                    if (!imageQueue.offer(baos.toByteArray(), 100, TimeUnit.MILLISECONDS)) {
                        System.out.println("[WARN] 图像处理队列已满，丢弃一帧");
                    }
                    broadcastImage(baos.toByteArray());
                }
            } catch (Exception e) {
                System.err.println("TCP处理异常: " + e.getMessage());
            }
        }).start();
    }
    private void startProcessingWorker() {
        processingExecutor.execute(() -> {
            while (running) {
                try {
                    byte[] imageData = imageQueue.poll(200, TimeUnit.MILLISECONDS);
//                    byte[] result =EdgeDetectionService.FastSobel(imageData);
                    if (imageData != null) {
                        broadcastImage(imageData);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);  // 添加新会话
        session.setBinaryMessageSizeLimit(1024 * 1024); // 保持1MB缓冲区
    }


        private void broadcastImage(byte[] imageData) {
            if (imageData != null) {
               boolean result = sendQueue.offer(imageData); // 非阻塞式放入队列
            }
        }

    private void sendToAllSessions(byte[] data) {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    byte[] result = DnnTest.DnnD1(data);
                    if (result != null) {
                        session.sendMessage(new BinaryMessage(result));
                    }
                } catch (IOException e) {
                    System.err.println("发送失败: " + e.getMessage());
                    sessions.remove(session);
                }
            }
        }
    }
    private void startSendWorker() {
        sendExecutor.execute(() -> {
            while (running) {
                try {
                    byte[] data = sendQueue.poll(50, TimeUnit.MILLISECONDS);
                    if (data != null) {
                        sendToAllSessions(data);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }


    @PreDestroy
    public void destroy() {
        running = false;
        tcpExecutor.shutdownNow();
        processingExecutor.shutdownNow();
        sendExecutor.shutdownNow();
        System.out.println("TCP服务器已关闭");
    }


}
