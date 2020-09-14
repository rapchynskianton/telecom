package com.itacademy.jd2.raa.telecom.dao.api;

import java.util.List;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.filter.BranchFilter;

public interface IBranchDao extends IDao<IBranch, Integer> {

	List<IBranch> find(BranchFilter filter);

	long getCount(BranchFilter filter);

}
