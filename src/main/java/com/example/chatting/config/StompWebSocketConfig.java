package com.example.chatting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // 웹소켓 연결 End-Point (ws://localhost:8080/chat)
                .setAllowedOriginPatterns("*");
//        registry.setErrorHandler(); -> 에러 핸들러: 소켓통신 중 예외 발생 시 해당 핸들러로 제어권이 넘어감
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setPathMatcher(new AntPathMatcher(".")); // "/" 대신 경로를 "."으로 표현 (AMQP 관례)
        registry.setApplicationDestinationPrefixes("/pub"); // Application Controller에서 처리하기 위한 요청 prefix
        registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // 클라이언트가 들어오는 경계(interceptor)에 대한 설정
//        registration.interceptors();
    }
}
