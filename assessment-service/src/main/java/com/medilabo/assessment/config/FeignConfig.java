package com.medilabo.assessment.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor(
            @Value("${medilab.services.username}") String username,
            @Value("${medilab.services.password}") String password
    ) {
        String token = Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));

        return template -> template.header("Authorization", "Basic " + token);
    }
}
