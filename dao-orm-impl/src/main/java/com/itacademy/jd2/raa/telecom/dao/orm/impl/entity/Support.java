package com.itacademy.jd2.raa.telecom.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;

@Entity
public class Support extends BaseEntity implements ISupport {
	@Column
	private String problemName;
	@Column
	private String problem;
	@Column
	private Boolean status;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserCabinet.class)
	private IUserCabinet userCabinet;

	public String getProblemName() {
		return problemName;
	}

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public IUserCabinet getUserCabinet() {
		return userCabinet;
	}

	public void setUserCabinet(IUserCabinet userCabinet) {
		this.userCabinet = userCabinet;
	}

}
