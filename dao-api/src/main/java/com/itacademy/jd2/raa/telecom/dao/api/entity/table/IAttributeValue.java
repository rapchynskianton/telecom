package com.itacademy.jd2.raa.telecom.dao.api.entity.table;

public interface IAttributeValue extends IBaseEntity {

	IAttribute getAttribute();
	
	void setAttribute(IAttribute attribute);
	
	Integer getValue();
	
	void setValue(Integer value);
}
