package com.itacademy.jd2.raa.telecom.dao.api;

import java.util.List;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeFilter;

public interface IAttributeDao extends IDao<IAttribute, Integer> {
	List<IAttribute> find(AttributeFilter filter);

	long getCount(AttributeFilter filter);

}
