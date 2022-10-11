package com.longyu.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.longyu.common", "com.longyu.admin"})
public class LongyuAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongyuAdminApplication.class, args);
    }

}
