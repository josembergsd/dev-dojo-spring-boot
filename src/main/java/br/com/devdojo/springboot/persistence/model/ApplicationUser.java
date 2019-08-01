package br.com.devdojo.springboot.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class ApplicationUser extends AbstractEntity{
	
	@NotEmpty(message="This field username is not empty")
	@Column(unique=true)
	private String username;
	@NotEmpty(message="The field password is not empty")
	private String password;
	
	private Integer isAdmin;
	
	public ApplicationUser() {
	}
	public ApplicationUser(ApplicationUser applicationUser) {
		this.username = applicationUser.username;
		this.password = applicationUser.password;
		this.isAdmin = applicationUser.isAdmin;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
}
