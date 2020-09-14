package com.itacademy.jd2.raa.telecom.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserCabinetFilter;

public interface IUserCabinetService {
	IUserCabinet createEntity();

	@Transactional
	void save(IUserCabinet entity);

	IUserCabinet get(Integer id);

	@Transactional
	void delete(Integer id);

	List<IUserCabinet> getAll();

	List<IUserCabinet> find(UserCabinetFilter filter);

	long getCount(UserCabinetFilter filter);

	@Transactional
	void deleteAll();

	IUserCabinet getFullInfo(Integer id);

	Integer getSum(Integer id);
}
