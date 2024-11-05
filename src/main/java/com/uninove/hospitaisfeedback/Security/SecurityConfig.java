package com.uninove.hospitaisfeedback.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desabilitar CSRF se não for necessário
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permitir todas as requisições
                );
        return http.build();
    }
}//