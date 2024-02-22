package com.wolfcode.blog.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlogDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogDataApplication.class, args);
    }

}
