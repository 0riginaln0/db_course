package com.example.db_course.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtConfigProperties(String secretKey) {
    
}
