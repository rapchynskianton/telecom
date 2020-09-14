package com.itacademy.jd2.raa.telecom.jdbc.impl.entity;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;

public class Branch extends BaseEntity implements IBranch {
	private String region;
	private String adress;
	private String telephone;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
