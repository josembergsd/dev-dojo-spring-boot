package br.com.devdojo.springboot.persistence.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.devdojo.springboot.persistence.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long>{
	List<Student> findByNameIgnoreCaseContaining(String name);
}
