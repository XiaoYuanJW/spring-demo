package com.example.demo.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 路由模式消息发送者
 * Created by YuanJW on 2022/10/30.
 */
@Slf4j
public class DirectSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String directExchange = "direct.exchange";

    private final String[] keys = {"red", "yellow", "green", "black"};

    public void send(int index) {
        StringBuilder sb = new StringBuilder();
        int limitIndex = index % 4;
        String key = keys[limitIndex];
        sb.append(key).append(' ').append(index + 1);
        String message = sb.toString();
        rabbitTemplate.convertAndSend(directExchange, key, message);
        log.info("send：{}", message);
    }
}
