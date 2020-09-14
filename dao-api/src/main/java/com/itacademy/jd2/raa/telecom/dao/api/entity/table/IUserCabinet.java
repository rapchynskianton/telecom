package com.itacademy.jd2.raa.telecom.dao.api.entity.table;

import java.util.Date;

public interface IUserCabinet extends IBaseEntity {

	Date getActivationDate();

	void setActivationDate(Date activationDate);

	Boolean getStatus();

	void setStatus(Boolean status);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	IBranch getBranch();

	void setBranch(IBranch branch);
}
