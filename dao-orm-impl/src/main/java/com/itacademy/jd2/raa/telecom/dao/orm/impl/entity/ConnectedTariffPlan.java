package com.itacademy.jd2.raa.telecom.dao.orm.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;

@Entity
public class ConnectedTariffPlan extends BaseEntity implements IConnectedTariffPlan {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserCabinet.class)
	private IUserCabinet userCabinet;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = TariffPlan.class)
	private ITariffPlan tariffPlan;

	@Column
	private Date activationDate;
	@Column
	private BigDecimal sumCost;

	public IUserCabinet getUserCabinet() {
		return userCabinet;
	}

	public void setUserCabinet(IUserCabinet userCabinet) {
		this.userCabinet = userCabinet;
	}

	public ITariffPlan getTariffPlan() {
		return tariffPlan;
	}

	public void setTariffPlan(ITariffPlan tariffPlan) {
		this.tariffPlan = tariffPlan;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public BigDecimal getSumCost() {
		return sumCost;
	}

	public void setSumCost(BigDecimal sumCost) {
		this.sumCost = sumCost;
	}

}
