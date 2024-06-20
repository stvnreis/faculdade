package com.fourdev.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fourdev.webapi.application.config.SwaggerConfig;

@SpringBootApplication(scanBasePackages = {"com.fourdev"}, exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories(basePackages = {"com.fourdev.webapi.infrastructure"})
@EntityScan(basePackages = {"com.fourdev.webapi.domain"})
public class WebapiApplication {

    public static void main(String[] args) {

        SpringApplication.run(WebapiApplication.class, args);
    }
}
