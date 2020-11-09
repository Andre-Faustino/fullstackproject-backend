package com.andrefaustino.fullstackproject.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.andrefaustino.fullstackproject.domain.Category;
import com.andrefaustino.fullstackproject.domain.Client;
import com.andrefaustino.fullstackproject.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	private Integer id;
	
	@NotEmpty(message = "Must not be empty")
	@Length(min=5, max=120, message = "Must have a range of 5 and 80 characters")
	private String name;
	
	@NotEmpty(message = "Must not be empty")
	@Email(message = "Email inválido")
	private String email;
	
	public ClientDTO() {
		
	}
	
	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
