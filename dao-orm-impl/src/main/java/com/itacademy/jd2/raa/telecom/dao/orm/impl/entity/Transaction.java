package com.itacademy.jd2.raa.telecom.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;

@Entity
public class Transaction extends BaseEntity implements ITransaction {

	@Column
	private BigDecimal value;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserCabinet.class)
	private IUserCabinet userCabinet;

	@Column
	private String description;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public IUserCabinet getUserCabinet() {
		return userCabinet;
	}

	public void setUserCabinet(IUserCabinet userCabinet) {
		this.userCabinet = userCabinet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
