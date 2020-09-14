package com.itacademy.jd2.raa.telecom.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;

@Entity
public class AttributeValue extends BaseEntity implements IAttributeValue {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Attribute.class)
	private IAttribute attribute;

	@Column
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
