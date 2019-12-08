package com.codenation;

import com.codenation.entity.Log;
import com.codenation.service.LogService;
import com.codenation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Collections;
import java.util.Date;

@ServletComponentScan
@SpringBootApplication
public class CentralErrosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private LogService logService;

	@Override
	public void run(String... args) throws Exception {

		Log log = new Log("Título", "DEBUG", "Detalhes", new Date(), "127.0.0.1", "email@email.com", "NO TOKEN", "DEBUG");
		logService.save(Collections.singletonList(log));
	}

}
