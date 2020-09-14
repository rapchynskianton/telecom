package com.itacademy.jd2.raa.telecom.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class PassportDetailsDTO {
	@NotNull
	@Size(min = 2, max = 2)
	private String serial;
	@NotNull
	@Size(min = 7, max = 7)
	private String serialNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date dateOfIssue;
	@NotNull
	@Size(min = 14, max = 14)
	private String identificationNumber;
	@NotNull
	@Size(min = 1, max = 30)
	private String passportAuthority;
	private Date created;
	private Date updated;

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getPassportAuthority() {
		return passportAuthority;
	}

	public void setPassportAuthority(String passportAuthority) {
		this.passportAuthority = passportAuthority;
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

}
