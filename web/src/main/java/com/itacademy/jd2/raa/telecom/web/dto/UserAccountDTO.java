package com.itacademy.jd2.raa.telecom.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class UserAccountDTO {

	private Integer id;
	
	@Email
	@NotNull
	@Size(min = 1, max = 50)
	private String email;
	@NotNull
	@Size(min = 1, max = 20)
	private String userPassword;
	@NotNull
	@Size(min = 1, max = 20)
	private String role;
	@NotNull
	@Size(min = 1, max = 20)
	private String firstName;
	@NotNull
	@Size(min = 1, max = 20)
	private String lastName;
	@NotNull
	@Size(min = 1, max = 20)
	private String fathersName;
	@NotNull
	@Size(min = 1, max = 100)
	private String adress;
	@NotNull
	@Size(min = 1, max = 20)
	private String telephone;
	private Date created;
	private Date updated;
	private PassportDetailsDTO info = new PassportDetailsDTO();
//	@NotNull
//	@Valid

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public PassportDetailsDTO getInfo() {
		return info;
	}

	public void setInfo(PassportDetailsDTO info) {
		this.info = info;
	}

}
