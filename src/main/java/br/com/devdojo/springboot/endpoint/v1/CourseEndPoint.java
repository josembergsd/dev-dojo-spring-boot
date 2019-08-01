package br.com.devdojo.springboot.endpoint.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.devdojo.springboot.persistence.model.Course;
import br.com.devdojo.springboot.persistence.repository.CourseRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1/professor/course")
@Api
public class CourseEndPoint {
	
	private final CourseRepository repository;

	@Autowired
	public CourseEndPoint(CourseRepository repository) {
		super();
		this.repository = repository;
	}
	
	@ApiOperation(value = "Return a course base on it's id", response = Course.class)
	@GetMapping(path = "id")
	public ResponseEntity<?> getCourseById(@PathVariable Long id) {
		return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
	}

}
