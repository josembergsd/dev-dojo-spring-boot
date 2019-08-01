package br.com.devdojo.springboot;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.devdojo.springboot.persistence.model.Student;
import br.com.devdojo.springboot.persistence.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevDojoSpringBootApplicationTests {

	private StudentRepository studentDAO;
	
	@Test
	public void contextLoads() {
		getStudentById(5L);
		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
		Optional<Student> student = studentDAO.findById(id);
		return new ResponseEntity<Optional<Student>>(student, HttpStatus.OK);
	}

}
