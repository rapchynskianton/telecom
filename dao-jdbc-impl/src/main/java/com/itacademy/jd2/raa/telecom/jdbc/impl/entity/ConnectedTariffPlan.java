package com.itacademy.jd2.raa.telecom.jdbc.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;

public class ConnectedTariffPlan extends BaseEntity implements IConnectedTariffPlan {
	private IUserCabinet userCabinet;
	private ITariffPlan tariffPlan;
	private Date activationDate;
	private BigDecimal sumCost;

	public IUserCabinet getUserCabinet() {
		return userCabinet;
	}

	public void setUserCabinet(IUserCabinet userCabinet) {
		this.userCabinet = userCabinet;
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

	public ITariffPlan getTariffPlan() {
		return tariffPlan;
	}

	public void setTariffPlan(ITariffPlan tariffPlan) {
		this.tariffPlan = tariffPlan;
	}

}
