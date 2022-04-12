package com.test.websocket2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration // 클래스 선언 앞에 작성, 해당 클래스가 Bean의 설정을 할 것이라는 것을 의미
@EnableWebSocketMessageBroker //  is used to enable our WebSocket server
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { //  웹 소켓 연결을 구성하기 위한 메서드를 구현하고 제공(implements)
    

    /*
        registerSompEndpoints :  
        클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드 포인트를 등록
        엔드 포인트 구성에 withSockJS ()를 사용합니다.
        SockJS는 웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화하는 데 사용(Fallback?  어떤 기능이 약해지거나 제대로 동작하지 않을 때, 이에 대처하는 기능 또는 동작)

        configureMessageBroker :
        한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 하는 데 사용될 메시지 브로커를 구성
    
        registry.setApplicationDestinationPrefixes("/app");
        "/app" 시작되는 메시지가 message-handling methods으로 라우팅 되어야 한다는 것을 명시
    
        egistry.enableSimpleBroker("/topic");
        "topic" 시작되는 메시지가 메시지 브로커로 라우팅 되도록 정의
        메시지 브로커는 특정 주제를 구독 한 연결된 모든 클라이언트에게 메시지를 broadcast(? 송신 호스트가 전송한 데이터가 네트워크에 연결된 모든 호스트에 전송되는 방식)



    */

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
        /*
        * withSockJS()
        * 는 웹소켓을 지원하지 않는 브라우저에
        * 폴백 옵션을 활성화하는데 사용됩니다.
        * */
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }

}
