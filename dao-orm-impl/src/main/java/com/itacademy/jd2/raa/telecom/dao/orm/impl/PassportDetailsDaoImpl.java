package com.itacademy.jd2.raa.telecom.dao.orm.impl;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.raa.telecom.dao.api.IPassportDetailsDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.dao.orm.impl.entity.PassportDetails;

@Repository
public class PassportDetailsDaoImpl extends AbstractDaoImpl<IPassportDetails, Integer> implements IPassportDetailsDao {
 
	protected PassportDetailsDaoImpl() {
        super(PassportDetails.class);
    }

    @Override
    public IPassportDetails createEntity() {
        return new PassportDetails();
    }


}
