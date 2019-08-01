package br.com.devdojo.springboot.persistence.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.devdojo.springboot.persistence.model.Professor;

public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long>{
	List<Professor> findByNameIgnoreCaseContaining(String name);
	Professor findByEmail(String email);
}
