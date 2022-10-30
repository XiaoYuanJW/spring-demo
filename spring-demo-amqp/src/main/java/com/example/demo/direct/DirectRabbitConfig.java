package com.example.demo.direct;

import com.example.demo.fanout.FanoutReceiver;
import com.example.demo.fanout.FanoutSender;
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
        return new DirectExchange("direct.exchage");
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
    public Binding directBinding1a(DirectExchange directExchange, Queue directQueue1) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("a");
    }

    @Bean
    public Binding directBinding1c(DirectExchange directExchange, Queue directQueue1) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("c");
    }

    @Bean
    public Binding directBinding2b(DirectExchange directExchange, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("b");
    }

    @Bean
    public Binding directBinding2c(DirectExchange directExchange, Queue directQueue2) {
        return BindingBuilder.bind(directQueue2).to(directExchange).with("c");
    }

    @Bean
    public FanoutSender fanoutSender() {
        return new FanoutSender();
    }

    @Bean
    public FanoutReceiver fanoutReceiver() {
        return new FanoutReceiver();
    }
}
