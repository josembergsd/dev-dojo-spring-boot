package br.com.devdojo.springboot.persistence.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class Student extends AbstractEntity{
	
	@NotEmpty(message = "Campo nome é obrigatório")
	private String name;
	@Email
	@NotEmpty(message = "Campo e-mail é obrigatório")
	private String email;
	
	public Student() {
	}

	public Student(String name) {
		this.name = name;
	}
	
		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
}
