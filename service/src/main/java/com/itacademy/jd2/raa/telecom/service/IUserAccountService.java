package com.itacademy.jd2.raa.telecom.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserAccountFilter;

public interface IUserAccountService {
	IUserAccount get(Integer id);

	List<IUserAccount> getAll();

	@Transactional
	void save(IUserAccount entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IUserAccount createEntity();

	IPassportDetails createPassportDetailsEntity();

	List<IUserAccount> find(UserAccountFilter filter);

	IUserAccount getFullInfo(final Integer id);

	long getCount(UserAccountFilter filter);

	IUserAccount getUserAndEmail(String userEmail);
	
	IUserAccount getSearchUserAccount();
}
