package com.itacademy.jd2.raa.telecom.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;

public class PassportDetails extends BaseEntity implements IPassportDetails {

	private String serial;
	private String serialNumber;
	private Date dateOfIssue;
	private String identificationNumber;
	private String passportAuthority;
	private IUserAccount userAccount;

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String getSerial() {
		return serial;
	}

	@Override
	public void setSerial(String serial) {
		this.serial = serial;
	}

	@Override
	public String getSerialNumber() {
		return serialNumber;
	}

	@Override
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public String getIdentificationNumber() {
		return identificationNumber;
	}

	@Override
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	@Override
	public String getPassportAuthority() {
		return passportAuthority;
	}

	@Override
	public void setPassportAuthority(String passportAuthority) {
		this.passportAuthority = passportAuthority;
	}

}
