package br.com.devdojo.springboot.persistence.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.devdojo.springboot.persistence.model.Course;

/***
 * 
 * @author Josemberg
 *
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long>{

}
