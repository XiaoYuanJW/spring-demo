package com.example.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者测试
 * Created by YuanJW on 2022/10/18.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PublishTest {
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
        channel.queueDeclare("basic queue", false, false, false, null);
        // 发送消息
        String message = "hello rabbitmq";
        channel.basicPublish("", "basic queue", null, message.getBytes());
        log.info("发送消息成功：{}", message);
        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}
