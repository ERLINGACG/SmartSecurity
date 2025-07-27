package com.erling.service.websocket.stomp;

import com.erling.service.opencv.dnn.DnnDetectorServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Component
public class TcpImageService {

    DnnDetectorServiceTest  dnnDetectorServiceTest;

    private static final int TCP_PORT = 12345;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public TcpImageService(SimpMessagingTemplate messagingTemplate, DnnDetectorServiceTest dnnDetectorServiceTest) {
        this.messagingTemplate = messagingTemplate;
        this.dnnDetectorServiceTest=dnnDetectorServiceTest;
        startTcpServer();
    }

    private void startTcpServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TCP_PORT)) {
                System.out.println("TCP图片服务启动，端口：" + TCP_PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    handleTcpClient(clientSocket);
                }
            } catch (IOException e) {
                System.err.println("TCP服务异常: " + e.getMessage());
            }
        }).start();
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }

    private void handleTcpClient(Socket socket) {
        new Thread(() -> {
            try (DataInputStream input = new DataInputStream(socket.getInputStream())) {
                byte[] header = new byte[8];
                while (true) {
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
                    byte[] imageData = dnnDetectorServiceTest.detectTest(baos.toByteArray());
//                    System.out.println("收到图片数据 - 时间戳: " + timestamp
//                            + " | 长度: " + imageLength + "字节"
//                            + " | 前20字节: " + bytesToHex(Arrays.copyOfRange(imageData, 0, Math.min(20, imageData.length))));
                    String base64Image = Base64.getEncoder().encodeToString(imageData);
                    messagingTemplate.convertAndSend("/topic/images",
                            Collections.singletonMap("image", base64Image));
                            // 原始数据入队

                }
            } catch (Exception e) {
                System.err.println("TCP处理异常: " + e.getMessage());
            }
        }).start();
    }
}