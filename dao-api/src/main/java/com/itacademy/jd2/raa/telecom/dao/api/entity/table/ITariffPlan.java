package com.itacademy.jd2.raa.telecom.dao.api.entity.table;

import java.math.BigDecimal;
import java.util.Set;

public interface ITariffPlan extends IBaseEntity {
	String getName();

	void setName(String name);

	BigDecimal getCostPerUnit();

	void setCostPerUnit(BigDecimal costPerUnit);

	Integer getUnit();

	void setUnit(Integer unit);

	String getType();

	void setType(String type);

	Set<IAttributeValue> getAttributeValue();

	void setAttributeValue(Set<IAttributeValue> attributeValue);

	
}
