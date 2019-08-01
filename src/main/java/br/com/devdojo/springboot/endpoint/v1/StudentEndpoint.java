package br.com.devdojo.springboot.endpoint.v1;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.springboot.error.ResourceNotFoundException;
import br.com.devdojo.springboot.persistence.model.Student;
import br.com.devdojo.springboot.persistence.repository.StudentRepository;

/*
 * Ponto de acesso a API pelas aplicações cliente
 * */

@RestController
@RequestMapping("v1/students")
public class StudentEndpoint {
	
	private final StudentRepository studentDAO;
	
	//Injeção de dependência
	@Autowired
	public StudentEndpoint(StudentRepository studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	@GetMapping(path = "/list")
	public ResponseEntity<?> listAll(Pageable pageable){
		return new ResponseEntity<Iterable<Student>>(studentDAO.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(path = "protected/students/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") Long id,
			//Authentication authentication){ 
											@AuthenticationPrincipal UserDetails userDetails){
		//System.out.println(authentication);
		verifyIfStudentsExistis(id);
		Optional<Student> student = studentDAO.findById(id);
		return new ResponseEntity<Optional<Student>>(student, HttpStatus.OK);
	}
	
	@GetMapping(path = "protected/students/findByName/{name}")
	public ResponseEntity<?> getStudentByName(@PathVariable String name){
		return new ResponseEntity<List<Student>>(studentDAO.findByNameIgnoreCaseContaining(name) ,HttpStatus.OK);
	}
	
	@PostMapping(path = "admin/students")
	@Transactional(rollbackFor = Exception.class) //Excessão do tipo cheked
	public ResponseEntity<?> save(@Valid @RequestBody Student student){
		return new ResponseEntity<Student>(studentDAO.save(student), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path="admin/students/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id){
		verifyIfStudentsExistis(id);
		studentDAO.deleteById(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PutMapping(path = "admin/students")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> update(@Valid @RequestBody Student student){
		verifyIfStudentsExistis(student.getId());
		studentDAO.save(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}
	
	//Verifica se o id já existe
	private void verifyIfStudentsExistis(Long id) {
		if(!studentDAO.existsById(id)) {
			throw new ResourceNotFoundException("Student not found for ID: " + id);
		}
	}
}
