package com.itacademy.jd2.raa.telecom.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.raa.telecom.dao.api.IBranchDao;
import com.itacademy.jd2.raa.telecom.dao.api.IUserCabinetDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.BranchFilter;
import com.itacademy.jd2.raa.telecom.service.IBranchService;

@Service
public class BranchServiceImpl implements IBranchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BranchServiceImpl.class);
	private IBranchDao dao;
	private IUserCabinetDao userCabinetDao;

	@Autowired
	public BranchServiceImpl(IBranchDao dao, IUserCabinetDao userCabinetDao) {
		super();
		this.dao = dao;
		this.userCabinetDao = userCabinetDao;
	}

	@Override
	public IBranch createEntity() {
		return dao.createEntity();

	}

	@Override
	public void save(final IBranch entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new branch: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("branch updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IBranch get(Integer id) {
		final IBranch entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		final List<IUserCabinet> listUserCabinetDao = userCabinetDao.selectAll();
		int j = listUserCabinetDao.size();
		for (int i = 0; i < j; i++) {
			userCabinetDao.delete(listUserCabinetDao.get(i).getId());
		}

		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all branches");
		dao.deleteAll();
	}

	@Override
	public List<IBranch> getAll() {
		final List<IBranch> all = dao.selectAll();
		return all;
	}

	@Override
	public List<IBranch> find(BranchFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(BranchFilter filter) {
		return dao.getCount(filter);
	}
}
