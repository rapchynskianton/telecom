package com.itacademy.jd2.raa.telecom.jdbc.impl.entity;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;

public class Attribute extends BaseEntity implements IAttribute {

	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
