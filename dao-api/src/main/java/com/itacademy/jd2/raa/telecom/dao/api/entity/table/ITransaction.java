package com.itacademy.jd2.raa.telecom.dao.api.entity.table;

import java.math.BigDecimal;

public interface ITransaction extends IBaseEntity {

	BigDecimal getValue();

	void setValue(BigDecimal value);

	IUserCabinet getUserCabinet();

	void setUserCabinet(IUserCabinet userCabinet);

	String getDescription();

	void setDescription(String description);
}
