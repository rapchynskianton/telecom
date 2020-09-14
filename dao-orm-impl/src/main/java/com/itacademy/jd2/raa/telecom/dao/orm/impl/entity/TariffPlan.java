package com.itacademy.jd2.raa.telecom.dao.orm.impl.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;

@Entity
public class TariffPlan extends BaseEntity implements ITariffPlan {

	@JoinTable(name = "tariff_plan_2_attribute_value", joinColumns = {
			@JoinColumn(name = "tariff_plan_id") }, inverseJoinColumns = { @JoinColumn(name = "attribute_value_id") })
	@ManyToMany(targetEntity = AttributeValue.class, fetch = FetchType.LAZY)
	private Set<IAttributeValue> attributeValue = new HashSet<>();

	@Column
	private String name;

	@Column
	private BigDecimal costPerUnit;
	@Column
	private Integer unit;
	@Column
	private String type;

	public Set<IAttributeValue> getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(Set<IAttributeValue> attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCostPerUnit() {
		return costPerUnit;
	}

	public void setCostPerUnit(BigDecimal costPerUnit) {
		this.costPerUnit = costPerUnit;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
