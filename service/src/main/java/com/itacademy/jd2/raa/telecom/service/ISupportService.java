package com.itacademy.jd2.raa.telecom.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.filter.SupportFilter;

public interface ISupportService {

	ISupport createEntity();

	@Transactional
	void save(ISupport entity);

	ISupport get(Integer id);

	@Transactional
	void delete(Integer id);

	List<ISupport> getAll();

	List<ISupport> find(SupportFilter filter);

	long getCount(SupportFilter filter);

	@Transactional
	void deleteAll();

	ISupport getFullInfo(Integer id);

}
