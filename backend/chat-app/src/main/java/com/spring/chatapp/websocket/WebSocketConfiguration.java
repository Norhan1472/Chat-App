package com.spring.chatapp.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       // WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
        // to start the connection on this link to connect the front with back
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
        //to send and receive data to everyone on the same destination
        registry.enableSimpleBroker("/start");
        // the prefix
        registry.setApplicationDestinationPrefixes("/current");
    }
}
