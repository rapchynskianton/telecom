package com.itacademy.jd2.raa.telecom.dao.api.filter;

public class ConnectedTariffPlanFilter extends AbstractFilter {
	private boolean fetchTariffPlan;

	public boolean isFetchTariffPlan() {
		return fetchTariffPlan;
	}

	public void setFetchTariffPlan(boolean fetchAttribute) {
		this.fetchTariffPlan = fetchAttribute;
	}
}
