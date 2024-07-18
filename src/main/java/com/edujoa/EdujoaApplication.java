package com.edujoa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.edujoa.khj.main.dao")
public class EdujoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdujoaApplication.class, args);
	}
}
