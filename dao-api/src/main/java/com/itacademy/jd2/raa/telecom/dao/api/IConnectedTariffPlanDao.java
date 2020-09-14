package com.itacademy.jd2.raa.telecom.dao.api;

import java.util.List;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.ConnectedTariffPlanFilter;

public interface IConnectedTariffPlanDao extends IDao<IConnectedTariffPlan, Integer> {

	List<IConnectedTariffPlan> find(ConnectedTariffPlanFilter filter);

	long getCount(ConnectedTariffPlanFilter filter);

	IConnectedTariffPlan getFullInfo(Integer id);

}
