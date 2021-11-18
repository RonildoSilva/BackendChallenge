package com.example.user;

import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import com.example.producer.ProducerController;
import com.example.user.endpoint.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
@ComponentScan(basePackageClasses = UserController.class)
@ComponentScan(basePackageClasses = ProducerController.class)
@EntityScan({"com.example.domain.model"})
@EnableJpaRepositories({"com.example.domain.repository"})
@ComponentScan("com.example.domain")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner init (UserRepository userRepository){
		return args -> {
			for (int i=0; i<10; i++){
				Long id = Long.valueOf(i);
				String name = "user " + i;
				String email = "user" + i + "@mail.com";
				String login = "login_" + i;
				Date createdDate = new Date();
				String password = "pass"+i;

				userRepository.save(new User(
						id, name, email, login, createdDate, passwordEncoder.encode(password), (id == 1)
				));
			}
		};

	}
}
