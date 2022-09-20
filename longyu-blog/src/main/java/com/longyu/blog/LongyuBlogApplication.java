package com.longyu.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.longyu")
public class LongyuBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongyuBlogApplication.class, args);
    }

}
