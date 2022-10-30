package com.example.demo.work;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 工作模式消息发送者
 * Created by YuanJW on 2022/10/25.
 */
@Slf4j
public class WorkSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String QUEUE_NAME = "work.queue";

    public void send(int index) {
        StringBuilder sb = new StringBuilder("hello work");
        int limitIndex = index % 3 + 1;
        for (int i = 0; i < limitIndex; i++) {
            sb.append(".");
        }
        sb.append(index + 1);
        String message = sb.toString();
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
        log.info("send message: {}", message);
    }
}
