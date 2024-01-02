package com.example.sqch111.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.sqch111.proxy")
public class ProjectConfig {
}
