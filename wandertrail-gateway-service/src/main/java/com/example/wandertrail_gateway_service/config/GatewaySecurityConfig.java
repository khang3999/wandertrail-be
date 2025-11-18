package com.example.wandertrail_gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {
    private final ReactiveJwtDecoder jwtDecoder;

    public GatewaySecurityConfig(ReactiveJwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder; // Spring tự inject 
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // .cors(Customizer.withDefaults())
                .cors(cors -> cors.disable())
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/v1/auth/login", "/api/v1/auth/register", "/api/v1/auth/test", "/actuator/health").permitAll()
                        .pathMatchers(org.springframework.http.HttpMethod.OPTIONS).permitAll() // CHO PHÉP OPTIONS
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtSpec -> jwtSpec.jwtDecoder(jwtDecoder)));
        return http.build();
    }
}
