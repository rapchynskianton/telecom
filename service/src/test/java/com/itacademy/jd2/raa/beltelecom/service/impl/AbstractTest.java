package com.itacademy.jd2.raa.beltelecom.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.raa.telecom.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.service.IAttributeService;
import com.itacademy.jd2.raa.telecom.service.IAttributeValueService;
import com.itacademy.jd2.raa.telecom.service.IBranchService;
import com.itacademy.jd2.raa.telecom.service.IConnectedTariffPlanService;
import com.itacademy.jd2.raa.telecom.service.ISupportService;
import com.itacademy.jd2.raa.telecom.service.ITariffPlanService;
import com.itacademy.jd2.raa.telecom.service.ITransactionService;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")

public abstract class AbstractTest {
	@Autowired
	protected IUserAccountService userAccountService;
	@Autowired
	protected IAttributeService attributeService;
	@Autowired
	protected ITariffPlanService tariffPlanService;
	@Autowired
	protected IAttributeValueService attributeValueService;
	@Autowired
	protected IBranchService branchService;
	@Autowired
	protected IConnectedTariffPlanService connectedTariffPlanService;
	@Autowired
	protected IUserCabinetService userCabinetService;
	@Autowired
	protected ISupportService supportService;
	@Autowired
	protected ITransactionService transactionService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive
		connectedTariffPlanService.deleteAll();
		tariffPlanService.deleteAll();
		attributeValueService.deleteAll();
		attributeService.deleteAll();

		supportService.deleteAll();
		transactionService.deleteAll();
		userCabinetService.deleteAll();
		userAccountService.deleteAll();
		branchService.deleteAll();
	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	protected Timestamp getDateNow() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

	protected BigDecimal getRandomDecimal() {
		BigDecimal randFromDouble = new BigDecimal(Math.random());
		return randFromDouble;
	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	protected IAttributeValue saveNewAttributeValue() {

		final IAttributeValue entity = attributeValueService.createEntity();
		entity.setAttribute(saveNewAttribute());
		entity.setValue(getRandomObjectsCount());
		attributeValueService.save(entity);
		return entity;
	}

	protected IAttribute saveNewAttribute() {
		final IAttribute entity = attributeService.createEntity();
		entity.setName("name -" + getRandomPrefix());
		attributeService.save(entity);
		return entity;
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setEmail("email-" + getRandomPrefix());
		entity.setUserPassword(getRandomPrefix());

		final UserRole[] allRole = UserRole.values();
		final int randomIndex = Math.max(0, getRANDOM().nextInt(allRole.length) - 1);
		entity.setRole(allRole[randomIndex]);

		entity.setFirstName("firstName-" + getRandomPrefix());
		entity.setLastName("lastName-" + getRandomPrefix());
		entity.setFathersName("fathersName-" + getRandomPrefix());
		entity.setAdress("adress-" + getRandomPrefix());
		entity.setTelephone("telephone-" + getRandomPrefix());
		userAccountService.save(entity);
		return entity;
	}

	protected ITariffPlan saveNewTariffPlan() {
		final ITariffPlan entity = tariffPlanService.createEntity();
		entity.setName(getRandomPrefix());
		entity.setCostPerUnit(getRandomDecimal());
		entity.setUnit(getRandomObjectsCount());
		entity.setType(getRandomPrefix());
		tariffPlanService.save(entity);
		return entity;
	}

	protected IBranch saveNewBranch() {
		final IBranch entity = branchService.createEntity();
		entity.setRegion(getRandomPrefix());
		entity.setAdress(getRandomPrefix());
		entity.setTelephone(getRandomPrefix());
		branchService.save(entity);
		return entity;
	}

	protected IConnectedTariffPlan saveNewConnectedTariffPlan() {
		final IConnectedTariffPlan entity = connectedTariffPlanService.createEntity();
		entity.setTariffPlan(saveNewTariffPlan());
		entity.setActivationDate(getDateNow());
		entity.setSumCost(getRandomDecimal());
		final IUserCabinet entityUserCabinet = saveNewUserCabinet();
		entity.setUserCabinet(entityUserCabinet);
		connectedTariffPlanService.save(entity);
		return entity;
	}

	protected IUserCabinet saveNewUserCabinet() {
		final IUserCabinet entity = userCabinetService.createEntity();
		entity.setActivationDate(getDateNow());
		entity.setStatus(true);
		entity.setUserAccount(saveNewUserAccount());
		entity.setBranch(saveNewBranch());
		userCabinetService.save(entity);
		return entity;
	}

	protected ISupport saveNewSupport() {
		final ISupport entity = supportService.createEntity();
		entity.setProblemName(getRandomPrefix());
		entity.setProblem(getRandomPrefix());
		entity.setStatus(true);
		entity.setUserCabinet(saveNewUserCabinet());
		entity.setUserAccount(saveNewUserAccount());
		supportService.save(entity);
		return entity;
	}
}
