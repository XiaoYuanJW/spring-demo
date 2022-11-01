package com.example.demo.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由模式配置类
 * Created by YuanJW on 2022/10/30.
 */
@Configuration
public class DirectRabbitConfig {
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct.exchange");
    }

    @Bean
    public Queue directQueue1() {
        return new Queue("direct.queue1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("direct.queue2");
    }

    @Bean
    public Binding directBindingRed1(DirectExchange directExchange, Queue directQueue1) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("red");
    }

    @Bean
    public Binding directBindingBlack1(DirectExchange directExchange, Queue directQueue1) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("black");
    }

    @Bean
    public Binding directBindingYellow2(DirectExchange directExchange, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("yellow");
    }

    @Bean
    public Binding directBindingBlack2(DirectExchange directExchange, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("black");
    }

    @Bean
    public DirectSender directSender() {
        return new DirectSender();
    }

    @Bean
    public DirectReceiver directReceiver() {
        return new DirectReceiver();
    }
}
