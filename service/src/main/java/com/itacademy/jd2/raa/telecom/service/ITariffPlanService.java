package com.itacademy.jd2.raa.telecom.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TariffPlanFilter;

public interface ITariffPlanService {
	ITariffPlan createEntity();

	@Transactional
	void save(ITariffPlan entity);

	ITariffPlan get(Integer id);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	List<ITariffPlan> getAll();

	long getCount(TariffPlanFilter filter);

	List<ITariffPlan> find(TariffPlanFilter filter);

	ITariffPlan getFullInfo(Integer id);

}