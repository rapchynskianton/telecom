package com.itacademy.jd2.raa.telecom.dao.api;

import java.util.List;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.filter.SupportFilter;

public interface ISupportDao extends IDao<ISupport, Integer> {

	List<ISupport> find(SupportFilter filter);

	long getCount(SupportFilter filter);

	ISupport getFullInfo(Integer id);

}
