package com.gusrubin.springauthorizationserver.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.gusrubin.springauthorizationserver.infrastructure")
public class ApplicationConfig {

    @Bean
    public ModelMapper getModelMapper() {
	return new ModelMapper();
    }

}
