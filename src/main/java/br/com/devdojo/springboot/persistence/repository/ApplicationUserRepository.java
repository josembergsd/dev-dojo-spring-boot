package br.com.devdojo.springboot.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.devdojo.springboot.persistence.model.ApplicationUser;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long>{
	ApplicationUser findByUsername(String username);
}
