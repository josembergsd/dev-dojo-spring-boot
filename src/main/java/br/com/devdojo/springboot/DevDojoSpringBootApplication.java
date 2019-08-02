package br.com.devdojo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DevDojoSpringBootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DevDojoSpringBootApplication.class, args);
	}
	
	@RestController
	class HelloWord{
		@GetMapping("/")
		String hello() {
			return "Olá mundo";
		}
	}
	
}
 