package br.com.devdojo.springboot.persistence.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 *@author Josemberg Duarte 
 */

@Entity
public class Course extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="The field name is cannot be empty")
	@ApiModelProperty(notes = "The name of the course")
	private String name;
	@ManyToOne(optional=false)
	private Professor professor;

}
