package com.erling.service.websocket.handler;

import com.erling.lib.opencv.dnn.DnnTest;
import com.erling.service.opencv.dnn.DnnDetectorServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClineWebSocketHandler extends BinaryWebSocketHandler {


    private static final List<WebSocketSession> sessions = new ArrayList<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        session.setBinaryMessageSizeLimit(16*1024 * 1024); // 保持1MB缓冲区
        System.out.println("New connection established: " + session.getId()); // 添加连接日志
    }
    public static void broadcast(byte[] imageData) {
        sessions.forEach(session -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new BinaryMessage(imageData));
                }
            } catch (IOException e) {
                System.err.println("向前端发送消息失败: " + e.getMessage());
            }
        });
    }
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException {
        byte[] bytes = message.getPayload().array();
        ClineWebSocketHandler.broadcast(DnnTest.DnnD1(bytes));
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.println("Connection closed: " + session.getId() + " with status: " + status); // 添加连接关闭日志
    }
}
