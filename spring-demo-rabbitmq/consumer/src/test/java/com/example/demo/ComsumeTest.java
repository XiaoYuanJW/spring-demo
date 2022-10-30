package com.example.demo;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者测试
 * Created by YuanJW on 2022/10/18.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComsumeTest {
    @Test
    public void contextLoads() throws IOException, TimeoutException {
        // 建立连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置连接参数：主机名、端口号、虚拟主机、用户名、密码
        connectionFactory.setHost("121.37.84.213");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 建立连接
        Connection connection = connectionFactory.newConnection();
        // 创建通道channel
        Channel channel = connection.createChannel();
        // 创建队列
        channel.queueDeclare("basic.queue", false, false, false, null);
        // 订阅消息
        channel.basicConsume("basic.queue", true, new DefaultConsumer(channel) {
               @Override
               public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                   // 处理消息（异步回调）
                   String message = new String(body);
                   log.info("接收到消息：{}", message);
               }
        });
        log.info("等待接受消息");
    }
}
