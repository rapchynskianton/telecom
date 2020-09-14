package com.itacademy.jd2.raa.telecom.dao.api.entity.table;

import java.util.Date;

public interface IPassportDetails extends IBaseEntity {

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	String getSerial();

	void setSerial(String serial);

	String getSerialNumber();

	void setSerialNumber(String serialNumber);

	Date getDateOfIssue();

	void setDateOfIssue(Date dateOfIssue);

	String getIdentificationNumber();

	void setIdentificationNumber(String identificationNumber);

	String getPassportAuthority();

	void setPassportAuthority(String passportAuthority);

}
