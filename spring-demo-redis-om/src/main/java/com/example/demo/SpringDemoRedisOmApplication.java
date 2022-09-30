package com.example.demo;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动人口
 * Created by YuanJW on 2022/9/28.
 */
@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "com.example.demo.*")
public class SpringDemoRedisOmApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDemoRedisOmApplication.class, args);
    }
}
