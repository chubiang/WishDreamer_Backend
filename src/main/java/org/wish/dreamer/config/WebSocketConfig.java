package org.wish.dreamer.config;

import jakarta.annotation.Resource;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.wish.dreamer.handler.AlarmHandler;

public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private AlarmHandler alarmHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(alarmHandler, "/alarm");
    }


}
