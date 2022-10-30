package com.example.demo.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 发布/订阅消息发送者
 * Created by YuanJW on 2022/10/27.
 */
@Slf4j
public class FanoutSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "fanout.exchange";

    public void send(int index) {
        StringBuilder sb = new StringBuilder();
        int limitIndex = index % 3 + 1;
        for (int i = 0; i < limitIndex; i++) {
            sb.append('.');
        }
        sb.append(index);
        String message = sb.toString();
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", message);
        log.info("send：{}", message);
    }
}
