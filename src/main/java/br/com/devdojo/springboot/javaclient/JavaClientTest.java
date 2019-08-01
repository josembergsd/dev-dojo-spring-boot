package br.com.devdojo.springboot.javaclient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import br.com.devdojo.springboot.persistence.model.Student;

public class JavaClientTest {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplateBuilder()
				.rootUri("http://localhost:8080/v1/protected")
				.basicAuthentication("josemberg", "js72446066")
				.build();
		
		Student student = restTemplate.getForObject("/students/{id}", Student.class, 1);
		System.out.println("Student no getForObject: " + student.getName());
	}
}
