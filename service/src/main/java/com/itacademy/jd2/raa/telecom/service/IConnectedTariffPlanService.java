package com.itacademy.jd2.raa.telecom.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.ConnectedTariffPlanFilter;

public interface IConnectedTariffPlanService {

	IConnectedTariffPlan createEntity();

	@Transactional
	void save(IConnectedTariffPlan entity);

	IConnectedTariffPlan get(Integer id);

	@Transactional
	void delete(Integer id);

	List<IConnectedTariffPlan> getAll();

	List<IConnectedTariffPlan> find(ConnectedTariffPlanFilter filter);

	long getCount(ConnectedTariffPlanFilter filter);

	@Transactional
	void deleteAll();

	IConnectedTariffPlan getFullInfo(Integer id);

}
