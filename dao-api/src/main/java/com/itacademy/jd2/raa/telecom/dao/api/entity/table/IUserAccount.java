package com.itacademy.jd2.raa.telecom.dao.api.entity.table;

import com.itacademy.jd2.raa.telecom.dao.api.entity.enums.UserRole;

public interface IUserAccount extends IBaseEntity {

	String getEmail();

	void setEmail(String email);

	String getUserPassword();

	void setUserPassword(String userPassword);

	UserRole getRole();

	void setRole(UserRole role);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getFathersName();

	void setFathersName(String fathersName);

	String getAdress();

	void setAdress(String adress);

	String getTelephone();

	void setTelephone(String telephone);

	IPassportDetails getPassportDetails();

	void setPassportDetails(IPassportDetails passportDetails);

}
