package com.example.demo.work;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * 工作模式消息接收者
 * Created by YuanJW on 2022/10/25.
 */
@Slf4j
@RabbitListener(queues = "work.queue")
public class WorkReceiver {
    private final int instance;

    public WorkReceiver(int instance) {
        this.instance = instance;
    }

    @RabbitHandler
    public void receive(String message) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("instance {} receive {}", this.instance, message);
        doWork(message);
        stopWatch.stop();
        log.info("instance {} Done in {}", this.instance, stopWatch.getTotalTimeMillis());
    }

    private void doWork(String message) {
        for (char ch : message.toCharArray()) {
            if (ch == '.') {
                ThreadUtil.sleep(3000);
            }
        }
    }
}
