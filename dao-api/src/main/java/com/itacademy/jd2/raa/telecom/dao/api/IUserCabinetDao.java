package com.itacademy.jd2.raa.telecom.dao.api;

import java.util.List;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserCabinetFilter;

public interface IUserCabinetDao extends IDao<IUserCabinet, Integer> {

	List<IUserCabinet> find(UserCabinetFilter filter);

	long getCount(UserCabinetFilter filter);

	IUserCabinet getFullInfo(Integer id);

}
