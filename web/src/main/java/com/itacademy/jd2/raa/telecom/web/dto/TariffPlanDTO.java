package com.itacademy.jd2.raa.telecom.web.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TariffPlanDTO {

	private Integer id;

	@Size(min = 1, max = 20)
	private String name;

	@NotNull
	private BigDecimal costPerUnit;

	@NotNull
	private Integer unit;

	@NotNull
	@Size(min = 1, max = 20)
	private String type;

	private String nameAttribute;

	private Integer valueAttribute;

	private Set<Integer> attributeValueIds;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNameAttribute() {
		return nameAttribute;
	}

	public void setNameAttribute(String nameAttribute) {
		this.nameAttribute = nameAttribute;
	}

	public Integer getValueAttribute() {
		return valueAttribute;
	}

	public void setValueAttribute(Integer valueAttribute) {
		this.valueAttribute = valueAttribute;
	}

	public Set<Integer> getAttributeValueIds() {
		return attributeValueIds;
	}

	public void setAttributeValueIds(Set<Integer> attributeValueIds) {
		this.attributeValueIds = attributeValueIds;
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
