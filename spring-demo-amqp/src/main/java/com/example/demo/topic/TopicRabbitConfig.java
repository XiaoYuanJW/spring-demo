package com.example.demo.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通配符模式消息队列配置类
 * Created by YuanJW on 2022/10/31.
 */
@Configuration
public class TopicRabbitConfig {
    @Bean
    public TopicExchange TopicExchange() {
        return new TopicExchange("topic.exchange");
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue("topic.queue1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topic.queue2");
    }

    @Bean
    public Queue topicQueue3() {
        return new Queue("topic.queue3");
    }

    @Bean
    public Binding TopicBinding1a(TopicExchange topicExchange, Queue topicQueue1) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("a.#");
    }

    @Bean
    public Binding TopicBinding1b(TopicExchange topicExchange, Queue topicQueue1) {
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("*.b.*");
    }

    @Bean
    public Binding TopicBinding2b(TopicExchange topicExchange, Queue topicQueue2) {
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("*.*.c");
    }

    @Bean
    public TopicSender topicSender() {
        return new TopicSender();
    }

    @Bean
    public TopicReceiver topicReceiver() {
        return new TopicReceiver();
    }
}
