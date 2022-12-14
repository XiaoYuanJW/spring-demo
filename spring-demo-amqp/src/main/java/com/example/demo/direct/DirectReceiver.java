package com.example.demo.direct;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * 路由模式消息接收者
 * Created by YuanJW on 2022/10/30.
 */
@Slf4j
public class DirectReceiver {
    @RabbitListener(queues = "direct.queue1")
    public void receive1(String message) {
        receive(message, 1);
    }

    @RabbitListener(queues = "direct.queue2")
    public void receive2(String message) {
        receive(message, 2);
    }

    @RabbitListener(bindings = @QueueBinding(
                value = @Queue(name = "direct.queue3"),
                exchange = @Exchange(name = "direct.exchange", type = ExchangeTypes.DIRECT),
                key = {"green", "black"}
            ))
    public void receive3(String message) {
        receive(message, 3);
    }

    public void receive(String message, int receive) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("instance {} receive : {}", receive, message);
        doWork(message);
        stopWatch.stop();
        log.info("instance {} done in {}s", receive, stopWatch.getTotalTimeMillis());
    }

    public void doWork(String message) {
        for (char ch : message.toCharArray()) {
            if (ch == '.') {
                ThreadUtil.sleep(1000);
            }
        }
    }
}
