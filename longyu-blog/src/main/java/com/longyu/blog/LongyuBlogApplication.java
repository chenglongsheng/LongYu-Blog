package com.longyu.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.longyu.common", "com.longyu.blog"})
public class LongyuBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongyuBlogApplication.class, args);
    }

}
