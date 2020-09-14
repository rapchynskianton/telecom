package com.itacademy.jd2.raa.telecom.dao.api.entity.table;

import java.math.BigDecimal;
import java.util.Date;

public interface IConnectedTariffPlan extends IBaseEntity {

	Date getActivationDate();

	void setActivationDate(Date activationDate);

	BigDecimal getSumCost();

	void setSumCost(BigDecimal sumCost);

	ITariffPlan getTariffPlan();

	void setTariffPlan(ITariffPlan tariffPlan);

	IUserCabinet getUserCabinet();

	void setUserCabinet(IUserCabinet userCabinet);
}
