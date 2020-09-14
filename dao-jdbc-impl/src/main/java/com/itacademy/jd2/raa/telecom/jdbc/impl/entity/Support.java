package com.itacademy.jd2.raa.telecom.jdbc.impl.entity;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;

public class Support extends BaseEntity implements ISupport {
	private String problemName;
	private String problem;
	private Boolean status;
	private IUserCabinet userCabinet;
	private IUserAccount userAccount;

	public String getProblemName() {
		return problemName;
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

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
