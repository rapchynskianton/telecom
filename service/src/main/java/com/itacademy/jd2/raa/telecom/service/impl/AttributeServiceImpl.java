package com.itacademy.jd2.raa.telecom.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.raa.telecom.dao.api.IAttributeDao;
import com.itacademy.jd2.raa.telecom.dao.api.IAttributeValueDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeFilter;
import com.itacademy.jd2.raa.telecom.service.IAttributeService;

@Service
public class AttributeServiceImpl implements IAttributeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AttributeServiceImpl.class);
	private IAttributeDao dao;
	private IAttributeValueDao attributeValueDao;

	@Autowired
	public AttributeServiceImpl(IAttributeDao dao, IAttributeValueDao attributeValueDao) {
		super();
		this.dao = dao;
		this.attributeValueDao = attributeValueDao;
	}

	@Override
	public IAttribute createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IAttribute entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new attribute name: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("attribute updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IAttribute get(Integer id) {
		final IAttribute entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {

		final List<IAttributeValue> list = attributeValueDao.selectAll();
		int j = list.size();
		for (int i = 0; i < j; i++) {
			attributeValueDao.delete(list.get(i).getId());
		}

		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all attributs");

		dao.deleteAll();
	}

	@Override
	public List<IAttribute> getAll() {
		final List<IAttribute> all = dao.selectAll();
		return all;
	}

	@Override
	public List<IAttribute> find(AttributeFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(AttributeFilter filter) {
		return dao.getCount(filter);
	}

}
