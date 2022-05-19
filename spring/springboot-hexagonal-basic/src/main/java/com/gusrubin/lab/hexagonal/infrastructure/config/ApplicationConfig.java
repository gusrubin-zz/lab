package com.gusrubin.lab.hexagonal.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Gustavo Rubin
 */

@Eager
@Configuration
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.gusrubin.lab.hexagonal.infrastructure")
public class ApplicationConfig {

	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

}
