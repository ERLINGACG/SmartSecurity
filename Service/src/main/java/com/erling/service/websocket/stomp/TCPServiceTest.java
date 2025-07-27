package com.erling.service.websocket.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Base64;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TCPServiceTest {
    private static final int TCP_PORT = 12347;
    private final SimpMessagingTemplate  messagingTemplate;
    private final ExecutorService clientThreadPool = Executors.newFixedThreadPool(10); // 根据需求调整线程数

    @Autowired
    public TCPServiceTest(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        startTCPServer();
    }

    protected void startTCPServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TCP_PORT)){
                System.out.println("TCP图片服务启动，端口：" + TCP_PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
//                    handleTcpClient(clientSocket);
                    handleTcpClient_Pool(clientSocket);
                }
            } catch (Exception e) {
              System.err.println("TCP<UNK>: " + e.getMessage());
            }
        }).start();
    }
    private void handleTcpClient(Socket clientSocket) throws Exception {
        new Thread(() -> {
            try (DataInputStream input = new DataInputStream(clientSocket.getInputStream())) {
                while (true) {
                    // 读取8字节消息头（2字节字符标记 + 2字节保留位 + 4字节消息长度）
                    byte[] header = new byte[40];
                    input.readFully(header);
                    ByteBuffer buffer = ByteBuffer.wrap(header).order(ByteOrder.LITTLE_ENDIAN);

                    byte[] topicBytes = new byte[32];
                    buffer.get(topicBytes);  // 读取32字节topic
                    int topicLength = buffer.getInt();  // 读取4字节topic长度
                    int bodyLength = buffer.getInt();   // 读取4字节消息体长度
                    // 验证topic长度有效性
                    if (topicLength < 0 || topicLength > 32) {
                        throw new IOException("无效的topic长度: " + topicLength);
                    }
                    String topic = new String(topicBytes, 0, topicLength, "UTF-8");

                    // 分块读取消息体（保持与TcpImageService相同的读取逻辑）
                    ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream(bodyLength);
                    byte[] chunk = new byte[1024 * 10];
                    int remaining = bodyLength;

                    while (remaining > 0) {
                        int read = input.read(chunk, 0, Math.min(chunk.length, remaining));
                        if (read <= 0) throw new IOException("流意外结束");
                        messageBuffer.write(chunk, 0, read);
                        remaining -= read;
                    }

//                    String message = messageBuffer.toString(StandardCharsets.UTF_8);//文本消息
                    byte[] binaryData = messageBuffer.toByteArray(); //二进制消息
                    System.out.printf("[%s|%d] 收到图片数据，大小: %d bytes%n", topic, bodyLength, binaryData.length);
                    String base64Image = Base64.getEncoder().encodeToString(binaryData);
                    messagingTemplate.convertAndSend(topic, Collections.singletonMap("image", base64Image));
//                    String saveDir = "received_images/";
//                    new File(saveDir).mkdirs(); // 创建目录
//                    String filename = saveDir + "img_" + System.currentTimeMillis() + ".jpg";
//                    try (FileOutputStream fos = new FileOutputStream(filename)) {
//                        fos.write(binaryData);
//                        System.out.println("图片已保存至: " + filename);
//                    } catch (IOException e) {
//                        System.err.println("保存失败: " + e.getMessage());
//                    }
                }
            } catch (EOFException e) {
                System.out.println("连接正常关闭");
            } catch (Exception e) {
                System.out.println("处理异常: " + e.getMessage());
            }
        }).start();
    }
    private void handleTcpClient_Pool(Socket clientSocket) {
        clientThreadPool.submit(() -> {
            try (DataInputStream input = new DataInputStream(clientSocket.getInputStream())) {
                while (true) {
                    try {
                        byte[] header = new byte[40];
                        input.readFully(header);
                        ByteBuffer buffer = ByteBuffer.wrap(header).order(ByteOrder.LITTLE_ENDIAN);

                        byte[] topicBytes = new byte[32];
                        buffer.get(topicBytes);  // 读取32字节topic
                        int topicLength = buffer.getInt();  // 读取4字节topic长度
                        int bodyLength = buffer.getInt();   // 读取4字节消息体长度
                        // 验证topic长度有效性
                        if (topicLength < 0 || topicLength > 32) {
                            throw new IOException("无效的topic长度: " + topicLength);
                        }
                        String topic = new String(topicBytes, 0, topicLength, "UTF-8");

                        // 分块读取消息体（保持与TcpImageService相同的读取逻辑）
                        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream(bodyLength);
                        byte[] chunk = new byte[1024 * 10];
                        int remaining = bodyLength;

                        while (remaining > 0) {
                            int read = input.read(chunk, 0, Math.min(chunk.length, remaining));
                            if (read <= 0) throw new IOException("流意外结束");
                            messageBuffer.write(chunk, 0, read);
                            remaining -= read;
                        }

//                      String message = messageBuffer.toString(StandardCharsets.UTF_8);//文本消息
                        byte[] binaryData = messageBuffer.toByteArray(); //二进制消息
                        System.out.printf("[%s|%d] 收到图片数据，大小: %d bytes%n", topic, bodyLength, binaryData.length);
                        String base64Image = Base64.getEncoder().encodeToString(binaryData);
                        messagingTemplate.convertAndSend(topic, Collections.singletonMap("image", base64Image));
                    } catch (Exception e) {
                        System.out.println("客户端处理异常: " + e.getMessage());
                        break;
                    }
                }
            } catch (EOFException e) {
                System.out.println("连接正常关闭");
            } catch (Exception e) {
                System.out.println("客户端处理异常: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("关闭连接异常: " + e.getMessage());
                }
            }
        });
    }

    private boolean containsNewLine(byte[] data) {
        for (byte b : data) {
            if (b == '\n' || b == '\r') {
                return true;
            }
        }
        return false;
    }
}
