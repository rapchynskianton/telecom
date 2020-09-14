package com.itacademy.jd2.raa.telecom.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.raa.telecom.dao.api.ITransactionDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.raa.telecom.service.ITransactionService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;

@Service
public class TransactionServiceImpl implements ITransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	private ITransactionDao dao;

	@Autowired
	public TransactionServiceImpl(ITransactionDao dao) {
		super();
		this.dao = dao;
	}

	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private IUserCabinetService userCabinetService;

	@PostConstruct
	private void emulator() {

		/*
		 * ScheduledExecutorService service =
		 * Executors.newSingleThreadScheduledExecutor();
		 * service.scheduleWithFixedDelay(new Runnable() {
		 * 
		 * @Override public void run() { LOGGER.info("execute transaction emulator");
		 */
		final List<IUserCabinet> list = userCabinetService.getAll();
		int count = list.size();

		for (int i = 0; i < count; i++) {

			final ITransaction entity = transactionService.createEntity();
			BigDecimal value = new BigDecimal(-1);
			entity.setDescription("minus " + value);
			entity.setValue(value);

			final IUserCabinet userCabinet = userCabinetService.createEntity();
			int x = list.get(i).getId();
			userCabinet.setId(x);

			entity.setUserCabinet(userCabinet);

			final Date modifedOn = new Date();
			entity.setCreated(modifedOn);
			entity.setUpdated(modifedOn);
			transactionService.save(entity);

		}

		/*
		 * }
		 * 
		 * 
		 * }, 0, 5, TimeUnit.SECONDS);
		 */
	}

	@Override
	public ITransaction createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITransaction entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			BigDecimal value = entity.getValue();
			entity.setDescription("add " + value);
			dao.insert(entity);
			LOGGER.info("new saved attribute value: {}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public ITransaction get(final Integer id) {
		final ITransaction entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public List<ITransaction> getAll() {
		final List<ITransaction> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<ITransaction> find(final TransactionFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final TransactionFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all attributs value");

		dao.deleteAll();
	}

	@Override
	public ITransaction getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
