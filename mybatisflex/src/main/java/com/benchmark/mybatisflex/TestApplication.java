package com.benchmark.mybatisflex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author：薛承城
 * @description：一句话描述
 * @date：2026/5/1 19:17
 */
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.benchmark.mybatisflex")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
