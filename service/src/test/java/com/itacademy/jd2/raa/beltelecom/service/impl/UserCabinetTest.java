package com.itacademy.jd2.raa.beltelecom.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.dao.api.filter.UserCabinetFilter;

public class UserCabinetTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IUserCabinet entity = saveNewUserCabinet();
		final IUserCabinet entityFromDb = userCabinetService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertTrue(entityFromDb.getStatus());
		assertNotNull(entityFromDb.getUserAccount());
		assertNotNull(entityFromDb.getBranch());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IUserCabinet entity = userCabinetService.createEntity();
		entity.setActivationDate(getDateNow());
		entity.setStatus(true);
		entity.setUserAccount(saveNewUserAccount());
		entity.setBranch(saveNewBranch());
		userCabinetService.save(entity);

		final IUserCabinet entityFromDb = userCabinetService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertTrue(entityFromDb.getStatus());
		assertNotNull(entityFromDb.getUserAccount());
		assertNotNull(entityFromDb.getBranch());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
	}

	@Test
	public void testGetAll() {
		final int intialCount = userCabinetService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewUserCabinet();
		}

		final List<IUserCabinet> allEntities = userCabinetService.getAll();

		for (final IUserCabinet entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertTrue(entityFromDb.getStatus());
			assertNotNull(entityFromDb.getUserAccount());
			assertNotNull(entityFromDb.getBranch());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IUserCabinet entity = saveNewUserCabinet();
		userCabinetService.delete(entity.getId());
		assertNull(userCabinetService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewUserCabinet();
		userCabinetService.deleteAll();
		assertEquals(0, userCabinetService.getAll().size());
	}

	@Test
	public void testGetCount() {

		final IUserCabinet entity1 = saveNewUserCabinet();
		userCabinetService.save(entity1);

		final UserCabinetFilter filter = new UserCabinetFilter();
		final long count1 = userCabinetService.getCount(filter);

		final IUserCabinet entity2 = saveNewUserCabinet();
		userCabinetService.save(entity2);

		long count2 = userCabinetService.getCount(filter);

		assertEquals(count1, count2 - 1);
	}

	@Test
	public void testFind() {

		final IUserCabinet entity1 = saveNewUserCabinet();
		userCabinetService.save(entity1);

		final UserCabinetFilter filter = new UserCabinetFilter();
		userCabinetService.find(filter);

	}

}
