package com.example.wandertrail_gateway_service.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        // 1. Cho phép Frontend (React)
        corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));

        // 2. Cho phép các method
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // 3. Cho phép Header
        corsConfig.setAllowedHeaders(Collections.singletonList("*"));
        corsConfig.setAllowCredentials(true);
        // 4. Cache preflight request (để trình duyệt không hỏi liên tục)
        corsConfig.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
