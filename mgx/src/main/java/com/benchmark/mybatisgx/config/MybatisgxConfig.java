package com.benchmark.mybatisgx.config;

import com.mybatisgx.boot.MybatisgxScan;
import com.mybatisgx.executor.keygen.KeyGenerator;
import com.mybatisgx.executor.keygen.SnowKeyGenerator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisGX 配置类
 */
@Configuration
@MybatisgxScan(
        entityBasePackages = "com.benchmark.mybatisgx.entity",
        daoBasePackages = "com.benchmark.mybatisgx.mapper",
        annotationClass = Mapper.class
)
public class MybatisgxConfig {

    @Bean
    public KeyGenerator keyGenerator() {
        return new SnowKeyGenerator();
    }
}
