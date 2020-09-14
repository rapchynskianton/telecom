package com.itacademy.jd2.raa.telecom.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.raa.telecom.dao.api.ISupportDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.filter.SupportFilter;
import com.itacademy.jd2.raa.telecom.service.ISupportService;

@Service
public class SupportServiceImpl implements ISupportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SupportServiceImpl.class);

	private ISupportDao dao;

	@Autowired
	public SupportServiceImpl(ISupportDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ISupport createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ISupport entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved support: {}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public ISupport get(final Integer id) {
		final ISupport entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public List<ISupport> getAll() {
		final List<ISupport> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<ISupport> find(final SupportFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final SupportFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all supports");
		dao.deleteAll();
	}

	public ISupport getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
