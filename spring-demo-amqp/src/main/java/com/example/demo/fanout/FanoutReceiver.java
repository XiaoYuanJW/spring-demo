package com.example.demo.fanout;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * 发布/订阅消息接收者
 * Created by YuanJW on 2022/10/27.
 */
@Slf4j
public class FanoutReceiver {
    @RabbitListener(queues = "fanout.queue1")
    public void receive1(String message) {
        receive(message, 1);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void receive2(String message) {
        receive(message, 2);
    }

    private void receive(String message, int receiver) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("instance {} receive {}", receiver, message);
        doWork(message);
        stopWatch.stop();
        log.info("instance {} done in {}s", receiver, stopWatch.getTotalTimeMillis());
    }

    private void doWork(String message) {
        for (char ch : message.toCharArray()) {
            if (ch == '.') {
                ThreadUtil.sleep(1000);
            }
        }
    }
}
