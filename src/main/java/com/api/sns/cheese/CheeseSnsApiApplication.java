package com.api.sns.cheese;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.api.sns.cheese.repository")
public class CheeseSnsApiApplication {

	public static void main(String[] args) {
        SpringApplication.run(CheeseSnsApiApplication.class, args);
    }
}
