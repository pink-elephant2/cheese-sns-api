package com.api.sns.cheese.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	DozerBeanMapper getDozerBeanMapperFactoryBean() {
		return new DozerBeanMapper();
	}
}
