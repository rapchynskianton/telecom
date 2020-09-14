package com.itacademy.jd2.raa.telecom.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.filter.BranchFilter;

public interface IBranchService {
	IBranch createEntity();
	 @Transactional
	void save(IBranch entity);

	IBranch get(Integer id);
	 @Transactional
	void delete(Integer id);
	 @Transactional
	void deleteAll();

	List<IBranch> getAll();

	List<IBranch> find(BranchFilter filter);

	long getCount(BranchFilter filter);
}
