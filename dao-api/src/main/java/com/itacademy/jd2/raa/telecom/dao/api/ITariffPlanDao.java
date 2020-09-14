package com.itacademy.jd2.raa.telecom.dao.api;

import java.util.List;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TariffPlanFilter;

public interface ITariffPlanDao extends IDao<ITariffPlan, Integer>{

    List<ITariffPlan> find(TariffPlanFilter filter);

    long getCount(TariffPlanFilter filter);

	ITariffPlan getFullInfo(Integer id);
	
}
