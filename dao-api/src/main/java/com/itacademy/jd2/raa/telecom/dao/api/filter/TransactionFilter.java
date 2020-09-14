package com.itacademy.jd2.raa.telecom.dao.api.filter;

public class TransactionFilter extends AbstractFilter {
	private Integer id;
	private boolean fetchUserCabinet;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isFetchUserCabinet() {
		return fetchUserCabinet;
	}

	public void setFetchUserCabinet(boolean fetchUserCabinet) {
		this.fetchUserCabinet = fetchUserCabinet;
	}

}
