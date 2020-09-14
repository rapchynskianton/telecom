package com.itacademy.jd2.raa.telecom.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.raa.telecom.dao.api.IConnectedTariffPlanDao;
import com.itacademy.jd2.raa.telecom.dao.api.ISupportDao;
import com.itacademy.jd2.raa.telecom.dao.api.ITransactionDao;
import com.itacademy.jd2.raa.telecom.dao.api.IUserCabinetDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserCabinetFilter;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;

@Service
public class UserCabinetServiceImpl implements IUserCabinetService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserCabinetServiceImpl.class);

	private IUserCabinetDao dao;
	private ITransactionDao transactionDao;
	private ISupportDao supportDao;
	private IConnectedTariffPlanDao connectedTariffPlanDao;

	@Autowired
	public UserCabinetServiceImpl(IUserCabinetDao dao, ITransactionDao transactionDao, ISupportDao supportDao,
			IConnectedTariffPlanDao connectedTariffPlanDao) {
		super();
		this.dao = dao;
		this.transactionDao = transactionDao;
		this.supportDao = supportDao;
		this.connectedTariffPlanDao = connectedTariffPlanDao;
	}

	@Override
	public IUserCabinet createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IUserCabinet entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved user cabinet: {}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IUserCabinet get(final Integer id) {
		final IUserCabinet entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);

		final List<IConnectedTariffPlan> listConnectedTariffPlan = connectedTariffPlanDao.selectAll();
		int allConnectedTariffPlan = listConnectedTariffPlan.size();
		for (int i = 0; i < allConnectedTariffPlan; i++) {
			connectedTariffPlanDao.delete(listConnectedTariffPlan.get(i).getId());
		}

		final List<ITransaction> list = transactionDao.selectAll();
		int allTransaction = list.size();
		for (int i = 0; i < allTransaction; i++) {
			transactionDao.delete(list.get(i).getId());
		}

		final List<ISupport> listSupport = supportDao.selectAll();
		int allSupport = listSupport.size();
		for (int i = 0; i < allSupport; i++) {
			supportDao.delete(listSupport.get(i).getId());
		}

		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all user accounts");
		dao.deleteAll();
	}

	@Override
	public List<IUserCabinet> getAll() {
		final List<IUserCabinet> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IUserCabinet> find(final UserCabinetFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final UserCabinetFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IUserCabinet getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

	@Override
	public Integer getSum(Integer id) {
		return transactionDao.getSum(id);
	}

}
