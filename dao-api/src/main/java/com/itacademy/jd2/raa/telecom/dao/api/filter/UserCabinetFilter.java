package com.itacademy.jd2.raa.telecom.dao.api.filter;

public class UserCabinetFilter extends AbstractFilter {
	private Integer id;
	private Integer idUserCabinet;
	private boolean fetchUserAccount;
	private boolean fetchBranch;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdUserCabinet() {
		return idUserCabinet;
	}

	public void setIdUserCabinet(Integer idUserCabinet) {
		this.idUserCabinet = idUserCabinet;
	}

	public boolean isFetchUserAccount() {
		return fetchUserAccount;
	}

	public void setFetchUserAccount(boolean fetchUserAccount) {
		this.fetchUserAccount = fetchUserAccount;
	}

	public boolean isFetchBranch() {
		return fetchBranch;
	}

	public void setFetchBranch(boolean fetchBranch) {
		this.fetchBranch = fetchBranch;
	}

}
