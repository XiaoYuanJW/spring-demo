package com.example.demo.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 简单模式消息发送者
 * Created by YuanJW on 2022/10/25.
 */
@Slf4j
@RabbitListener(queues = "simple.queue")
public class SimpleReceiver {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    public void receive(String message) throws InterruptedException {
        log.info("receive : {}", message);
    }

    @RabbitListener(queues = "object.queue")
    public void receiveObject(Map<String, Object> map) {
        log.info("receive : {}", map);
    }
}
