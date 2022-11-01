package com.example.demo.topic;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * 通配符模式消息发送者
 * Created by YuanJW on 2022/10/31.
 */
@Slf4j
public class TopicReceiver {
    @RabbitListener(queues = "topic.queue1")
    public void receive1(String message) {
        receive(message, 1);
    }
    @RabbitListener(queues = "topic.queue2")
    public void receive2(String message) {
        receive(message, 2);
    }
    @RabbitListener(bindings = @QueueBinding(value = @Queue("topic.queue3"),
            exchange = @Exchange(name = "topic.exchange", type = ExchangeTypes.TOPIC),
            key = "#.d.#"
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

    private void doWork(String in){
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                ThreadUtil.sleep(1000);
            }
        }
    }
}
