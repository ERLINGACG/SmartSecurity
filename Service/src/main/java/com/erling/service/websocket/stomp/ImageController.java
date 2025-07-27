package com.erling.service.websocket.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

@Controller
@Component
public class ImageController {
    private final SimpMessagingTemplate messagingTemplate;

    public ImageController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    @MessageMapping("/image")
    public void handleImage(byte[] imageData) {
        try {
            // 将字节数组转换为BufferedImage
            ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
            BufferedImage image = ImageIO.read(bais);

            if (image != null) {
                // 打印图片基本信息
                String info = String.format("收到图片 尺寸: %dx%d 大小: %d bytes",
                        image.getWidth(), image.getHeight(), imageData.length);
                System.out.println(info);

                // 保留原始发送逻辑
                messagingTemplate.convertAndSend("/topic/images", imageData);

            } else {
                System.err.println("无效的JPEG格式");
            }
        } catch (IIOException e) {
            System.err.println("JPEG解码错误: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("处理异常: " + e.getMessage());
        }
    }

}
