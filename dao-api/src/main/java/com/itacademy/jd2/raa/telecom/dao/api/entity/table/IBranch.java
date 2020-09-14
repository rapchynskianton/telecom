package com.itacademy.jd2.raa.telecom.dao.api.entity.table;

public interface IBranch extends IBaseEntity {

	String getRegion();

	void setRegion(String region);

	String getAdress();

	void setAdress(String adress);

	String getTelephone();

	void setTelephone(String telephone);
}
