package com.hch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 该注解自动扫描父包下的component
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
