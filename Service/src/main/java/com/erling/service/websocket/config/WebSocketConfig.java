package com.erling.service.websocket.config;

import com.erling.service.websocket.handler.ClineWebSocketHandler;
import com.erling.service.websocket.handler.MyWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@EnableWebSocket
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/Esp32Stream").setAllowedOrigins("*");
        registry.addHandler(ClineHandler(), "/Esp32Stream/Cline1").setAllowedOrigins("*");
    }


    @Bean
    public WebSocketHandler myHandler() {
        return new MyWebSocketHandler();
    }
    @Bean
    public WebSocketHandler ClineHandler() {
        return new ClineWebSocketHandler();
    }

}
