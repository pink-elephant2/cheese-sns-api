package com.api.sns.cheese.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Config {

	@Bean
	DozerBeanMapper getDozerBeanMapperFactoryBean() {
		return new DozerBeanMapper();
	}

	/**
	 * パスワードハッシュ化方式 TODO WebSecurityConfigに移行する
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
