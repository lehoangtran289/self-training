package com.example.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.springmvc.*")
public class RootConfig {
    public RootConfig() {
        System.out.println("construct root config");
    }
}
