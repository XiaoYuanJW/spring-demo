package com.example.demo.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.example.demo.common.api.CommonResult;
import com.example.demo.fanout.FanoutSender;
import com.example.demo.simple.SimpleSender;
import com.example.demo.work.WorkSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * rabbitamqp控制器
 * Created by YuanJW on 2022/10/25.
 */
@RestController
@RequestMapping("/rabbit")
@Api(tags = "RabbitController", description = "RabbitMQ Amqp功能测试控制器")
public class RabbitController {
    @Resource
    private SimpleSender simpleSender;
    @Resource
    private WorkSender workSender;
    @Resource
    private FanoutSender fanoutSender;


    @ApiOperation(value = "简单模式消息队列发送消息")
    @GetMapping(value = "/simple")
    public CommonResult simple() {
        for (int i = 0; i < 10; i++) {
            simpleSender.send();
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "工作模式消息队列发送消息")
    @GetMapping(value = "/work")
    public CommonResult work() {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation(value = "发布/订阅模式消息队列发送消息")
    @GetMapping(value = "/fanout")
    public CommonResult fanout() {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }


}
