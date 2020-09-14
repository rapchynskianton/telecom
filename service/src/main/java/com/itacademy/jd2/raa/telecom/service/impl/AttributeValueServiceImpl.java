package com.itacademy.jd2.raa.telecom.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.raa.telecom.dao.api.IAttributeValueDao;
import com.itacademy.jd2.raa.telecom.dao.api.ITariffPlanDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeValueFilter;
import com.itacademy.jd2.raa.telecom.service.IAttributeValueService;

@Service
public class AttributeValueServiceImpl implements IAttributeValueService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttributeServiceImpl.class);

	private IAttributeValueDao dao;
	private ITariffPlanDao tariffPlanDao;

	@Autowired
	public AttributeValueServiceImpl(IAttributeValueDao dao, ITariffPlanDao tariffPlanDao) {
		super();
		this.dao = dao;
		this.tariffPlanDao = tariffPlanDao;
	}

	@Override
	public IAttributeValue createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IAttributeValue entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved attribute value: {}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IAttributeValue get(final Integer id) {
		final IAttributeValue entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		final List<ITariffPlan> list = tariffPlanDao.selectAll();
		int j = list.size();

		for (int i = 0; i < j; i++) {
			final Set<IAttributeValue> listAttribute = list.get(i).getAttributeValue();
			Iterator<IAttributeValue> iterator = listAttribute.iterator();

			while (iterator.hasNext()) {
				IAttributeValue item = iterator.next();
				if (item.getId() == id) {
					tariffPlanDao.delete(list.get(i).getId());
				}
			}
		}
		dao.delete(id);
	}

	@Override
	public List<IAttributeValue> getAll() {
		final List<IAttributeValue> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IAttributeValue> find(final AttributeValueFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final AttributeValueFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all attributs value");
		dao.deleteAll();
	}

	@Override
	public IAttributeValue getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}
}
