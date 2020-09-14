package com.itacademy.jd2.raa.telecom.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;

public class UserCabinet extends BaseEntity implements IUserCabinet {

	private Date activationDate;
	private Boolean status;
	private IUserAccount userAccount;
	private IBranch branch;

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public IBranch getBranch() {
		return branch;
	}

	public void setBranch(IBranch branch) {
		this.branch = branch;
	}

}
