package com.itacademy.jd2.raa.telecom.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.raa.telecom.dao.api.IPassportDetailsDao;
import com.itacademy.jd2.raa.telecom.dao.api.ISupportDao;
import com.itacademy.jd2.raa.telecom.dao.api.IUserAccountDao;
import com.itacademy.jd2.raa.telecom.dao.api.IUserCabinetDao;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;
import com.itacademy.jd2.raa.telecom.service.util.GenerateMD5;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	private IUserAccountDao dao;
	private IPassportDetailsDao passportDetailsDao;
	private ISupportDao supportDao;
	private IUserCabinetDao userCabinetDao;

	@Autowired
	public UserAccountServiceImpl(IUserAccountDao dao, IPassportDetailsDao passportDetailsDao, ISupportDao supportDao,
			IUserCabinetDao userCabinetDao) {
		super();
		this.dao = dao;
		this.passportDetailsDao = passportDetailsDao;
		this.supportDao = supportDao;
		this.userCabinetDao = userCabinetDao;
	}

	@Override
	public IPassportDetails createPassportDetailsEntity() {
		return passportDetailsDao.createEntity();
	}

	@Override
	public IUserAccount createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IUserAccount userAccount) {
		final Date modifiedDate = new Date();
		userAccount.setUpdated(modifiedDate);

		IPassportDetails info = userAccount.getPassportDetails();
		if (info != null) {
			info.setUpdated(modifiedDate);
		}
		if (userAccount.getId() == null) {
			userAccount.setCreated(modifiedDate);
			GenerateMD5 userPassword = new GenerateMD5();

			userAccount.setUserPassword(userPassword.getMD5Password(userAccount.getUserPassword(), (byte) 33));
			dao.insert(userAccount);
			if (info != null) {
				info.setId(userAccount.getId());
				info.setCreated(modifiedDate);
				info.setUserAccount(userAccount);
				passportDetailsDao.insert(info);

				// SendMailTLS mail = new SendMailTLS();
				// mail.NewEmail(userAccount.getEmail());
			}
		} else {
			dao.update(userAccount);
			if (info != null) {
				passportDetailsDao.update(info);
			}
		}
	}

	@Override
	public IUserAccount get(Integer id) {
		final IUserAccount entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		final List<ISupport> listSupport = supportDao.selectAll();
		int allSupport = listSupport.size();
		for (int i = 0; i < allSupport; i++) {
			supportDao.delete(listSupport.get(i).getId());
		}

		final List<IUserCabinet> listUserCabinet = userCabinetDao.selectAll();
		int allUserCabinet = listUserCabinet.size();
		for (int i = 0; i < allUserCabinet; i++) {
			userCabinetDao.delete(listUserCabinet.get(i).getId());
		}

		passportDetailsDao.delete(id);
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all user accounts");
		passportDetailsDao.deleteAll();
		dao.deleteAll();
	}

	@Override
	public List<IUserAccount> getAll() {
		final List<IUserAccount> all = dao.selectAll();
		return all;
	}

	@Override
	public IUserAccount getFullInfo(final Integer id) {
		final IUserAccount entity = dao.getFullInfo(id);
		return entity;
	}

	@Override
	public List<IUserAccount> find(final UserAccountFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final UserAccountFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public IUserAccount getUserAndEmail(String userEmail) {

		return dao.getUserAndEmail(userEmail);
	}

	@Override
	public IUserAccount getSearchUserAccount() {
		return dao.getSearchUserAccount();
	}

}
