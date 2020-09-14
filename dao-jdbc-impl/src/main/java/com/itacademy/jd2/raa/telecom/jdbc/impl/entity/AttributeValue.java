package com.itacademy.jd2.raa.telecom.jdbc.impl.entity;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;

public class AttributeValue extends BaseEntity implements IAttributeValue{

	private IAttribute attribute;
	private Integer value;

	public IAttribute getAttribute() {
		return attribute;
	}
	public void setAttribute(IAttribute attribute) {
		this.attribute = attribute;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}




}
