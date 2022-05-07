package com.proiect_refugiati_api.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer, WebSocketConfigurer {


//    ws://localhost:8080/app/topic/greetings

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
        registry.addEndpoint("/hello");
        registry.addEndpoint("/hello").withSockJS();
        registry.addEndpoint("/chat");
        registry.addEndpoint("/chat").withSockJS();
    }


    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketTextHandler(), "/user");
    }

}

//@Component
//public class SocketTextHandler extends TextWebSocketHandler {
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message)
//            throws InterruptedException, IOException {
//
//        String payload = message.getPayload();
//        JSONObject jsonObject = new JSONObject(payload);
//        session.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
//    }
//
//}
