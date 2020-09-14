package com.itacademy.jd2.raa.telecom.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class TransactionDTO {

	private Integer id;

	@NotNull
	private BigDecimal value;

	@NotNull
	private Integer userCabinetId;

	@NotNull
	private String description;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Integer getUserCabinetId() {
		return userCabinetId;
	}

	public void setUserCabinetId(Integer userCabinetId) {
		this.userCabinetId = userCabinetId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
