package com.example.utility_toolkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.utility_toolkit", "com.juk" })
public class UtilityToolkitApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilityToolkitApplication.class, args);
	}

}
