package com.techmentor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableAsync
public class TechMentorApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TechMentorApplication.class, args);
	}


}
