package com.itacademy.jd2.raa.telecom.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SupportDTO {
	private Integer id;

	@Size(min = 1, max = 20)
	@NotNull
	private String problemName;

	@NotNull
	private String problem;

	@NotNull
	private Boolean status;

	@NotNull
	private Integer userAccountId;

	private String userAccountEmail;

	@NotNull
	private Integer userCabinetId;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getUserAccountEmail() {
		return userAccountEmail;
	}

	public void setUserAccountEmail(String userAccountEmail) {
		this.userAccountEmail = userAccountEmail;
	}

	public Integer getUserCabinetId() {
		return userCabinetId;
	}

	public void setUserCabinetId(Integer userCabinetId) {
		this.userCabinetId = userCabinetId;
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
