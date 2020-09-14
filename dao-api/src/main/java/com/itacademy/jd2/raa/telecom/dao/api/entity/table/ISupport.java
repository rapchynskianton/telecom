
package com.itacademy.jd2.raa.telecom.dao.api.entity.table;

public interface ISupport extends IBaseEntity {

	String getProblemName();

	void setProblemName(String problemName);

	String getProblem();

	void setProblem(String problem);

	Boolean getStatus();

	void setStatus(Boolean status);

	IUserCabinet getUserCabinet();

	void setUserCabinet(IUserCabinet userCabinet);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);
}
