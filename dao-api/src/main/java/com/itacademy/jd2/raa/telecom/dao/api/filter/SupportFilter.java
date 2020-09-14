package com.itacademy.jd2.raa.telecom.dao.api.filter;

public class SupportFilter extends AbstractFilter {
	private Integer id;
	private boolean fetchUserCabinet;
	private boolean fetchUserAccount;

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

	public boolean isFetchUserAccount() {
		return fetchUserAccount;
	}

	public void setFetchUserAccount(boolean fetchUserAccount) {
		this.fetchUserAccount = fetchUserAccount;
	}

}
