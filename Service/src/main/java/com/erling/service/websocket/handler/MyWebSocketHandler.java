package com.erling.service.websocket.handler;

import com.erling.lib.opencv.dnn.DnnTest;
import com.erling.service.opencv.dnn.DnnDetectorServiceTest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MyWebSocketHandler extends BinaryWebSocketHandler {

    private DnnDetectorServiceTest  dnnDetectorServiceTest;
    @Autowired
    public void setDnnDetectorServiceTest(DnnDetectorServiceTest dnnDetectorServiceTest) {
        this.dnnDetectorServiceTest = dnnDetectorServiceTest;
    }

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    @Override
    public void afterConnectionEstablished(@NonNull  WebSocketSession session) {
        sessions.add(session);
        session.setBinaryMessageSizeLimit(16*1024 * 1024); // 保持1MB缓冲区
        System.out.println("New connection established: " + session.getId()); // 添加连接日志
    }


    @Override
    protected void handleBinaryMessage(@NonNull WebSocketSession session, BinaryMessage message) {
        byte[] imageBytes = message.getPayload().array();
        ClineWebSocketHandler.broadcast(dnnDetectorServiceTest.detectTest(imageBytes));
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session,@NonNull CloseStatus status) {
        sessions.remove(session);
    }
}
