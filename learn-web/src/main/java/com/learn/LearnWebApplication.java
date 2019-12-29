package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.learn.infrastructure.mapper" )
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class LearnWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnWebApplication.class, args);
    }

}
