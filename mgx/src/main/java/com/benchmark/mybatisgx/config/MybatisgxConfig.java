package com.benchmark.mybatisgx.config;

import com.mybatisgx.boot.MybatisgxScan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisGX 配置类
 */
@Configuration
@MybatisgxScan(
        entityBasePackages = "com.mybatisgx.benchmark.mybatisgx.entity",
        daoBasePackages = "com.mybatisgx.benchmark.mybatisgx.mapper",
        annotationClass = Mapper.class
)
public class MybatisgxConfig {
}
