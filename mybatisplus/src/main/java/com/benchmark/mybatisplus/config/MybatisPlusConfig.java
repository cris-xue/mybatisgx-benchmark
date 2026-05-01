package com.benchmark.mybatisplus.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 配置类
 */
@Configuration
@MapperScan("com.benchmark.mybatisplus.mapper")
public class MybatisPlusConfig {
}
