package com.itacademy.jd2.raa.telecom.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.raa.telecom.dao.api.IConnectedTariffPlanDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.ConnectedTariffPlanFilter;
import com.itacademy.jd2.raa.telecom.service.IConnectedTariffPlanService;

@Service
public class ConnectedTariffPlanServiceImpl implements IConnectedTariffPlanService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedTariffPlanServiceImpl.class);

    private IConnectedTariffPlanDao dao;

    @Autowired
    public ConnectedTariffPlanServiceImpl(IConnectedTariffPlanDao dao) {
        super();
        this.dao = dao;
    }

    @Override
    public IConnectedTariffPlan createEntity() {
        return dao.createEntity();
    }

    @Override
    public void save(final IConnectedTariffPlan entity) {
        final Date modifedOn = new Date();
        entity.setUpdated(modifedOn);
        if (entity.getId() == null) {
            entity.setCreated(modifedOn);
            dao.insert(entity);
            LOGGER.info("new saved connected service: {}", entity);
        } else {
            dao.update(entity);
        }
    }

    @Override
    public IConnectedTariffPlan get(final Integer id) {
        final IConnectedTariffPlan entity = dao.get(id);
        LOGGER.debug("entityById: {}", entity);
        return entity;
    }

    @Override
    public void delete(final Integer id) {
        LOGGER.info("delete entity: {}", id);
        dao.delete(id);
    }
	@Override
	public void deleteAll() {
		LOGGER.info("delete all connected tariff plan");
		dao.deleteAll();
	}
    
    @Override
    public List<IConnectedTariffPlan> getAll() {
        final List<IConnectedTariffPlan> all = dao.selectAll();
        LOGGER.debug("total count in DB: {}", all.size());
        return all;
    }

    @Override
    public List<IConnectedTariffPlan> find(final ConnectedTariffPlanFilter filter) {
        return dao.find(filter);
    }

    @Override
    public long getCount(final ConnectedTariffPlanFilter filter) {
        return dao.getCount(filter);
    }

    @Override
    public IConnectedTariffPlan getFullInfo(Integer id) {
        return dao.getFullInfo(id);
    }

	
	
}
