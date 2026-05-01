package com.benchmark.jpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * JPA 配置类
 */
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.benchmark.jpa.entity")
@EnableJpaRepositories(basePackages = "com.benchmark.jpa.repository")
public class JpaConfig {
}
