package com.itacademy.jd2.raa.telecom.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;

@Entity
public class Branch extends BaseEntity implements IBranch {
	@Column
	private String region;
	@Column
	private String adress;
	@Column
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
