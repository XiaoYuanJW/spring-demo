package com.example.demo.work;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 工作模式消息队列配置类
 * Created by YuanJW on 2022/10/25.
 */
@Configuration
public class WorkRabbitConfig {
    @Bean
    public Queue workQueue() {
        return new Queue("work.queue");
    }

    @Bean
    public WorkReceiver workReceiver1() {
        return new WorkReceiver(1);
    }

    @Bean
    public WorkReceiver workReceiver2() {
        return new WorkReceiver(2);
    }

    @Bean
    public WorkSender workSender() {
        return new WorkSender();
    }
}
