package com.codenation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class CentralErrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApplication.class, args);
	}
}
