package br.com.devdojo.springboot;

import javax.validation.Valid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.devdojo.springboot.persistence.model.Student;
import br.com.devdojo.springboot.persistence.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertStudentTest {
	@Autowired
	StudentRepository repository;

	@Test
	public void test() {
		for(int i = 1; i == 100; i++) {
			Student student = new Student();
			student.setName("Student" + i);
			student.setEmail("student" + i +"@" + "student" + i);
			save(student);
		}
	}
	
	@PostMapping
	@Transactional(rollbackFor = Exception.class) //Excessão do tipo cheked
	public ResponseEntity<?> save(@Valid @RequestBody Student student){
		return new ResponseEntity<Student>(repository.save(student), HttpStatus.CREATED);
	}

}
