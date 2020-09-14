package com.itacademy.jd2.raa.telecom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.raa.telecom.dao.api.IConnectedTariffPlanDao;
import com.itacademy.jd2.raa.telecom.dao.api.ITariffPlanDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TariffPlanFilter;
import com.itacademy.jd2.raa.telecom.service.ITariffPlanService;

@Service
public class TariffPlanServiceImpl implements ITariffPlanService {

	private ITariffPlanDao dao;
	private IConnectedTariffPlanDao connectedTariffPlanDao;

	@Autowired
	public TariffPlanServiceImpl(ITariffPlanDao dao, IConnectedTariffPlanDao connectedTariffPlanDao) {
		super();
		this.dao = dao;
		this.connectedTariffPlanDao = connectedTariffPlanDao;
	}

	@Override
	public ITariffPlan createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITariffPlan entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public ITariffPlan get(final Integer id) {
		final ITariffPlan entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		// remove all references
		final ITariffPlan iTariffPlan = dao.get(id);

		final List<IConnectedTariffPlan> list = connectedTariffPlanDao.selectAll();
		int j = list.size();
		for (int i = 0; i < j; i++) {
			connectedTariffPlanDao.delete(list.get(i).getId());
		}

		iTariffPlan.getAttributeValue().clear();
		dao.update(iTariffPlan);

		dao.delete(id);
	}

	@Override
	public ITariffPlan getFullInfo(final Integer id) {
		final ITariffPlan entity = dao.getFullInfo(id);
		return entity;
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<ITariffPlan> getAll() {
		final List<ITariffPlan> all = dao.selectAll();
		return all;
	}

	@Override
	public long getCount(TariffPlanFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<ITariffPlan> find(TariffPlanFilter filter) {
		return dao.find(filter);
	}
}
