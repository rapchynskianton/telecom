package com.itacademy.jd2.raa.telecom.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;

@Entity
public class Attribute extends BaseEntity implements IAttribute {

	@Column
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
