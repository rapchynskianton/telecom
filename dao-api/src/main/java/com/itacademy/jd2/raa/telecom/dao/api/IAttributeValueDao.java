package com.itacademy.jd2.raa.telecom.dao.api;

import java.util.List;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeValueFilter;

public interface IAttributeValueDao extends IDao<IAttributeValue, Integer> {

	List<IAttributeValue> find(AttributeValueFilter filter);

	long getCount(AttributeValueFilter filter);

	IAttributeValue getFullInfo(Integer id);

}
