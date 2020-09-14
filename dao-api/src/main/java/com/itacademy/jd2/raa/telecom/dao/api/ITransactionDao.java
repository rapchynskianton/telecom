package com.itacademy.jd2.raa.telecom.dao.api;

import java.util.List;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TransactionFilter;

public interface ITransactionDao extends IDao<ITransaction, Integer> {

	List<ITransaction> find(TransactionFilter filter);

	long getCount(TransactionFilter filter);

	ITransaction getFullInfo(Integer id);


}
