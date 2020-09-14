package com.itacademy.jd2.raa.telecom.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeValueFilter;

public interface IAttributeValueService {

	IAttributeValue get(Integer id);

	List<IAttributeValue> getAll();

	@Transactional
	void save(IAttributeValue entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IAttributeValue createEntity();

	List<IAttributeValue> find(AttributeValueFilter filter);

	long getCount(AttributeValueFilter filter);

	IAttributeValue getFullInfo(Integer id);

}
