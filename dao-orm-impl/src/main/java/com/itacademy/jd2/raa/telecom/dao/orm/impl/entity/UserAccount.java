package com.itacademy.jd2.raa.telecom.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.raa.telecom.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {
	@Column
	private String email;
	@Column
	private String userPassword;
	@Column
	@Enumerated(EnumType.STRING)
	private UserRole role;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String fathersName;
	@Column
	private String adress;
	@Column
	private String telephone;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount", targetEntity = PassportDetails.class)
	private IPassportDetails passportDetails;

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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
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

	public IPassportDetails getPassportDetails() {
		return passportDetails;
	}

	public void setPassportDetails(IPassportDetails passportDetails) {
		this.passportDetails = passportDetails;
	}

}
