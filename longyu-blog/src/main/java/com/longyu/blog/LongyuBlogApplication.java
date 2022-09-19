package com.longyu.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.longyu")
@MapperScan(basePackages = "com.longyu.common.mapper")
public class LongyuBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongyuBlogApplication.class, args);
    }

}
