package com.codenation;

import com.codenation.dto.UserDTO;
import com.codenation.entity.Log;
import com.codenation.entity.User;
import com.codenation.service.LogService;
import com.codenation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Collections;
import java.util.Date;
import java.time.LocalDateTime;

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

	//TODO Debug
	@Override
	public void run(String... args) throws Exception {
		User user = new User("Administrador", "email@email.com", "tr4df2g5wp", 3);
		userService.save(user);

		Log log = new Log("Título", "DEBUG", "Detalhes", new Date(), "127.0.0.1", "email@email.com", "NO TOKEN", "DEBUG");
		logService.save(Collections.singletonList(log));
	}

}
