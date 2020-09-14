package com.itacademy.jd2.raa.telecom.jdbc.impl.entity;

import com.itacademy.jd2.raa.telecom.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;

public class UserAccount extends BaseEntity implements IUserAccount {

	private String email;
	private String userPassword;
	private UserRole role;
	private String firstName;
	private String lastName;
	private String fathersName;
	private String adress;
	private String telephone;
	private IPassportDetails passportDetails;

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getUserPassword() {
		return userPassword;
	}

	@Override
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getFathersName() {
		return fathersName;
	}

	@Override
	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	@Override
	public String getAdress() {
		return adress;
	}

	@Override
	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public String getTelephone() {
		return telephone;
	}

	@Override
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public IPassportDetails getPassportDetails() {
		return passportDetails;
	}

	@Override
	public void setPassportDetails(IPassportDetails passportDetails) {
		this.passportDetails = passportDetails;
	}

}
