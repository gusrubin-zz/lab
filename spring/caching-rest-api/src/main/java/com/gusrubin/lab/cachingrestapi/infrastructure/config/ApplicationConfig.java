package com.gusrubin.lab.cachingrestapi.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableCaching
@EnableJpaRepositories(basePackages = "com.gusrubin.lab.cachingrestapi.infrastructure")
public class ApplicationConfig {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
