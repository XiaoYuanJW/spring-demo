package com.example.demo.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 简单模式消息发送者
 * Created by YuanJW on 2022/10/25.
 */
@Slf4j
public class SimpleSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final String QUEUE_NAME = "simple.queue";

    public void send() {
        String message = "hello simple";
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
        log.info("send message: {}", message);
    }
}
