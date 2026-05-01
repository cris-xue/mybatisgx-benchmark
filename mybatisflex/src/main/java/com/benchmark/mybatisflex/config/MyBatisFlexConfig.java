package com.benchmark.mybatisflex.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Flex 配置类
 */
@Configuration
@MapperScan("com.benchmark.mybatisflex.mapper")
public class MyBatisFlexConfig {
}
