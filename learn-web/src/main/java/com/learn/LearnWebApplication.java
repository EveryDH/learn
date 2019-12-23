package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.learn.infrastructure.mapper" )
@SpringBootApplication
public class LearnWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnWebApplication.class, args);
    }

}
