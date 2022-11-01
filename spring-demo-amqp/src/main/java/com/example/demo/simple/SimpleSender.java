package com.example.demo.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单模式消息发送者
 * Created by YuanJW on 2022/10/25.
 */
@Slf4j
public class SimpleSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final String QUEUE_NAME = "simple.queue";

    public static final String OBJECT_QUEUE = "object.queue";

    public void send() {
        String message = "hello simple";
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
        log.info("send message: {}", message);
    }

    public void sendObject() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("age", 20);
        rabbitTemplate.convertAndSend(OBJECT_QUEUE, map);
        log.info("send message: {}", map);
    }
}
