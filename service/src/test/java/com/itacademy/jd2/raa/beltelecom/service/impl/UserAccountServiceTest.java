package com.itacademy.jd2.raa.beltelecom.service.impl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.raa.telecom.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserAccountFilter;

public class UserAccountServiceTest extends AbstractTest {

	@Test
	public void createTest() {
		final IUserAccount entity = saveNewUserAccount();
		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertEquals(entity.getEmail(), entityFromDb.getEmail());
		assertNotNull(entityFromDb.getId());

		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void createWithFullInfo() {
		final IUserAccount entity = userAccountService.createEntity();

		entity.setEmail("email-" + getRandomPrefix());
		entity.setUserPassword("password-" + getRandomPrefix());
		final UserRole[] allRole = UserRole.values();
		final int randomIndex = Math.max(0, getRANDOM().nextInt(allRole.length) - 1);
		entity.setRole(allRole[randomIndex]);
		entity.setFirstName("firstName-" + getRandomPrefix());
		entity.setLastName("lastName-" + getRandomPrefix());
		entity.setFathersName("fathersName-" + getRandomPrefix());
		entity.setAdress("adress-" + getRandomPrefix());
		entity.setTelephone("telephone-" + getRandomPrefix());

		final IPassportDetails passportDetailsEntity = userAccountService.createPassportDetailsEntity();

		passportDetailsEntity.setSerial("sn");
		passportDetailsEntity.setSerialNumber(getRandomPrefix());
		Timestamp modifedOn = new Timestamp(System.currentTimeMillis());
		passportDetailsEntity.setDateOfIssue(modifedOn);
		passportDetailsEntity.setIdentificationNumber(getRandomPrefix());
		passportDetailsEntity.setPassportAuthority(getRandomPrefix());
		entity.setPassportDetails(passportDetailsEntity);

		userAccountService.save(entity);

		final IUserAccount entityFromDb = userAccountService.getFullInfo(entity.getId());

		assertEquals(entity.getEmail(), entityFromDb.getEmail());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

		final IPassportDetails passportDetailsEntityFromDb = entityFromDb.getPassportDetails();
		assertNotNull(passportDetailsEntityFromDb.getId());
		assertEquals(passportDetailsEntity.getSerial(), passportDetailsEntityFromDb.getSerial());
		assertNotNull(passportDetailsEntityFromDb.getCreated());
		assertNotNull(passportDetailsEntityFromDb.getUpdated());
		assertTrue(passportDetailsEntityFromDb.getCreated().equals(passportDetailsEntityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IUserAccount entity = saveNewUserAccount();
		String newEmail = entity.getEmail() + "_updated";
		entity.setEmail(newEmail);
		String newPassword = entity.getUserPassword() + "_updated";
		entity.setUserPassword(newPassword);

		Thread.sleep(2000);
		userAccountService.save(entity);

		final IUserAccount entityFromDb = userAccountService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getUserPassword());
		assertNotNull(entityFromDb.getRole());
		assertNotNull(entityFromDb.getFirstName());
		assertNotNull(entityFromDb.getLastName());
		assertNotNull(entityFromDb.getFathersName());
		assertNotNull(entityFromDb.getAdress());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
	}

	@Test
	public void testGetAll() {
		final int intialCount = userAccountService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUserAccount();
		}

		final List<IUserAccount> allEntities = userAccountService.getAll();

		for (final IUserAccount entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getEmail());
			assertNotNull(entityFromDb.getUserPassword());
			assertNotNull(entityFromDb.getRole());
			assertNotNull(entityFromDb.getFirstName());
			assertNotNull(entityFromDb.getLastName());
			assertNotNull(entityFromDb.getFathersName());
			assertNotNull(entityFromDb.getAdress());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IUserAccount entity = saveNewUserAccount();
		userAccountService.delete(entity.getId());
		assertNull(userAccountService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewUserAccount();
		userAccountService.deleteAll();
		assertEquals(0, userAccountService.getAll().size());
	}

	@Test
	public void testGetCount() {

		final IUserAccount entity1 = saveNewUserAccount();
		userAccountService.save(entity1);

		final UserAccountFilter filter = new UserAccountFilter();
		final long count1 = userAccountService.getCount(filter);

		final IUserAccount entity2 = saveNewUserAccount();
		userAccountService.save(entity2);

		long count2 = userAccountService.getCount(filter);

		assertEquals(count1, count2 - 1);
	}

	 @Test
	 public void testFind() {
	
	 final IUserAccount entity1 = saveNewUserAccount();
	 userAccountService.save(entity1);
	
	 final UserAccountFilter filter = new UserAccountFilter();
	 userAccountService.find(filter);
	
	 }

}