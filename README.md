# Spring-ChatServer-Demo
Spring STOMP + RabbitMQ를 활용한 그룹 채팅 기능 심플 데모

### 실행 방법

1. Spring 서버 실행 (로컬에 RabbitMQ가 실행되고 있어야함)
2. 클라이언트는 `ws://localhost:8080/chat` 웹소켓 서버 접속
3. 동시에 `/exchange/chat.exchange/*.room.{roomId}`로 subscribe
4. 특정 클라이언트에서 메시지를 publish하고 싶다면 `/pub/chat.{method}.{roomId}`로 MessageCreateDto 폼에 맞는 json 데이터 전송 (method는 enter, talk, exit 중 하나)
