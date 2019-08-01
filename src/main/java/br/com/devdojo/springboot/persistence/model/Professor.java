package br.com.devdojo.springboot.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class Professor extends AbstractEntity{
	
	@NotEmpty(message="The field name is not empty")
	private String name;
	@Email(message="The field email is not valid")
	@NotEmpty(message="The field e-mail is not empty")
	@Column(unique=true)
	private String email;
	
	private Professor() {
	}
	
	
	private Professor(String name, String email) {
		this.name = name;
		this.email = email;
	}


	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
