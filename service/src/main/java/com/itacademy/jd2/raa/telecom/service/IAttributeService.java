package com.itacademy.jd2.raa.telecom.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeFilter;

public interface IAttributeService {
	
	IAttribute get(Integer id);

	List<IAttribute> getAll();

	@Transactional
	void save(IAttribute entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IAttribute createEntity();

	List<IAttribute> find(AttributeFilter filter);

	long getCount(AttributeFilter filter);
}
