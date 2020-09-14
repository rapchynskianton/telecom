package com.itacademy.jd2.raa.telecom.jdbc.impl.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;

public class TariffPlan extends BaseEntity implements ITariffPlan {

	private String name;
	private BigDecimal costPerUnit;
	private Integer unit;
	private String type;
	private Set<IAttributeValue> attributeValue = new HashSet<>();

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BigDecimal getCostPerUnit() {
		return costPerUnit;
	}

	@Override
	public void setCostPerUnit(BigDecimal costPerUnit) {
		this.costPerUnit = costPerUnit;
	}

	@Override
	public Integer getUnit() {
		return unit;
	}

	@Override
	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Set<IAttributeValue> getAttributeValue() {
		return attributeValue;
	}

	@Override
	public void setAttributeValue(Set<IAttributeValue> attributeValue) {
		this.attributeValue = attributeValue;
	}

}
