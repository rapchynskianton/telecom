package com.itacademy.jd2.raa.telecom.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;

@Entity
public class UserCabinet extends BaseEntity implements IUserCabinet {
	@Column
	private Date activationDate;

	@Column
	private Boolean status;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Branch.class)
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
