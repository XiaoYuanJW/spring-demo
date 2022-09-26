package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * Created by YuanJW on 2022/9/23.
 */
@Configuration
@MapperScan("com.example.demo.dao")
public class MyBatisConfig {
}
