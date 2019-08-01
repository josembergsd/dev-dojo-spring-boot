package br.com.devdojo.springboot.endpoint.v1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.springboot.persistence.model.Professor;
import br.com.devdojo.springboot.persistence.repository.ProfessorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/professor")
@Api
public class ProfessorEndPoint {
	
	private final ProfessorRepository repository;
	
	@Autowired
	public ProfessorEndPoint(ProfessorRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(path = "{id}")
	@ApiOperation (value = "Find professor by his ID", notes = "We have to make  this method better", response = Professor.class)
	public ResponseEntity<?> getProfessorById(@PathVariable Long id){
		Optional<Professor> professor = repository.findById(id);
		return new ResponseEntity<Optional<Professor>>(professor,HttpStatus.OK);
	}
}
