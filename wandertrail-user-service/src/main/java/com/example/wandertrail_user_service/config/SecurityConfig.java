package com.example.wandertrail_user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails defaultUser = User.withUsername("admin")
    // .password(passwordEncoder().encode("123456"))
    // .roles("ADMIN")
    // .build();

    // return new InMemoryUserDetailsManager(defaultUser);
    // }
    // Tạo bean encode cho password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // tắt CSRF nếu muốn test service trực tiếp
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // mọi request đều được qua
                )
                .httpBasic(basic -> basic.disable()) // tắt HTTP Basic
                .formLogin(form -> form.disable()); // tắt Form Login;
        return http.build();
    }

    // @Bean
    // CorsConfigurationSource apiConfigurationSource() {
    // CorsConfiguration configuration = new CorsConfiguration();
    // configuration.setAllowedOrigins(List.of("http://localhost:3000"));
    // configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE",
    // "OPTIONS"));
    // configuration.setAllowedHeaders(List.of("*"));
    // configuration.setAllowCredentials(true);

    // UrlBasedCorsConfigurationSource source = new
    // UrlBasedCorsConfigurationSource();
    // source.registerCorsConfiguration("/**", configuration);
    // return source;
    // }

    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    // return new WebMvcConfigurer() {
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    // registry.addMapping("/**")
    // .allowedOrigins("http://localhost:3000")
    // .allowedMethods("*")
    // .allowedHeaders("*")
    // .allowCredentials(true);
    // }
    // };
    // }

}
