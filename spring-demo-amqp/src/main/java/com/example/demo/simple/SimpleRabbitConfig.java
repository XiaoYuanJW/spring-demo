package com.example.demo.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 简单模式消息队列配置类
 * Created by YuanJW on 2022/10/25.
 */
@Configuration
public class SimpleRabbitConfig {
    @Bean
    public Queue simpleQueue() {
        return new Queue("simple.queue");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object.queue");
    }

    @Bean
    public SimpleReceiver simpleReceiver (){
       return new SimpleReceiver();
    }

    @Bean
    public SimpleSender simpleSender() {
        return new SimpleSender();
    }
}
