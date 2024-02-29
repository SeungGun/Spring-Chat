package com.example.chatting.controller;


import com.example.chatting.dto.MessageCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final RabbitTemplate rabbitTemplate;

    @MessageMapping("chat.enter.{roomId}")
    public void enterUser(@DestinationVariable("roomId") Long roomId, @Payload MessageCreateDto messageCreateDto){

        rabbitTemplate.convertAndSend("chat.exchange", "enter.room." + roomId, messageCreateDto);
    }

    @MessageMapping("chat.talk.{roomId}")
    public void talkUser(@DestinationVariable("roomId") Long roomId, @Payload MessageCreateDto messageCreateDto){
        rabbitTemplate.convertAndSend("chat.exchange", "*.room." + roomId, messageCreateDto);
    }

    @MessageMapping("chat.exit.{roomId}")
    public void exitUser(@DestinationVariable("roomId") Long roomId, @Payload MessageCreateDto messageCreateDto){
        rabbitTemplate.convertAndSend("chat.exchange", "exit.room." + roomId, messageCreateDto);
    }
}
