package com.example.demo.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通配符模式消息发送者
 * Created by YuanJW on 2022/10/31.
 */
@Slf4j
public class TopicSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final String TOPIC_EXCHANGE = "topic.exchange";

    public final String[] keys ={"a.z.1", "1.b.2", "1.3.c", "2.d.1"};

    public void send(int index) {
        StringBuilder sb = new StringBuilder();
        int limitIndex = index % keys.length;
        String key = keys[limitIndex];
        sb.append(key).append(' ').append(index + 1);
        String message = sb.toString();
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, key, message);
        log.info("send：{}", message);
    }
}
