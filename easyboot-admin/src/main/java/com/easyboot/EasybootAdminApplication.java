package com.easyboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class EasybootAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasybootAdminApplication.class, args);
	}

}

