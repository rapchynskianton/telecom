package com.itacademy.jd2.raa.telecom.dao.api;

import java.util.List;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserAccountFilter;

public interface IUserAccountDao extends IDao<IUserAccount, Integer> {

	IUserAccount getFullInfo(final Integer id);

	List<IUserAccount> find(UserAccountFilter filter); // skip

	long getCount(UserAccountFilter filter);

	IUserAccount getUserAndEmail(String userEmail);

	IUserAccount getSearchUserAccount();

}
