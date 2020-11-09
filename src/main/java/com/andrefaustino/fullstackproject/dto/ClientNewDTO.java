package com.andrefaustino.fullstackproject.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.andrefaustino.fullstackproject.domain.Category;
import com.andrefaustino.fullstackproject.domain.Client;
import com.andrefaustino.fullstackproject.services.validation.ClientInsert;


@ClientInsert
public class ClientNewDTO implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@NotEmpty(message = "Must not be empty")
	@Length(min=5, max=120, message = "Must have a range of 5 and 80 characters")
	private String name;
	
	@NotEmpty(message = "Must not be empty")
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "Must not be empty")
	private String cpfcnpj;
	
	
	private Integer clientType;
	
	
	@NotEmpty(message = "Must not be empty")
	private String address;
	
	@NotEmpty(message = "Must not be empty")
	private String number;
	
	@NotEmpty(message = "Must not be empty")
	private String complement;
	
	
	private String district;
	private String zip;
	
	@NotEmpty(message = "Must not be empty")
	private String phone1;
	
	
	private String phone2;
	private String phone3;
	
	private Integer cityId;
	
	public ClientNewDTO() {
		
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

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
	
	
	
}
