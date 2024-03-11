package com.example.chatting.service;

import com.example.chatting.dto.MessageCreateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = "chat.queue")
public class RabbitService {

    @RabbitHandler
    public void receiveMessage(MessageCreateDto messageCreateDto){
        log.info("Consumer Result", messageCreateDto.getContent());
    }
}
