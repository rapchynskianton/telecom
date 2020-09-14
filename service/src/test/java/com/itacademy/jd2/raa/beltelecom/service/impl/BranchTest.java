package com.itacademy.jd2.raa.beltelecom.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.filter.BranchFilter;

public class BranchTest extends AbstractTest {
	@Test
	public void testCreate() {

		final IBranch entity = saveNewBranch();
		final IBranch entityFromDb = branchService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getRegion());
		assertNotNull(entityFromDb.getAdress());
		assertNotNull(entityFromDb.getTelephone());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IBranch entity = saveNewBranch();
		String newAdress = getRandomPrefix();
		entity.setAdress(newAdress);
		String newRegion = getRandomPrefix();
		entity.setRegion(newRegion);
		String newTelephone = getRandomPrefix();
		entity.setTelephone(newTelephone);

		Thread.sleep(2000);
		branchService.save(entity);

		final IBranch entityFromDb = branchService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getRegion());
		assertNotNull(entityFromDb.getAdress());
		assertNotNull(entityFromDb.getTelephone());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = branchService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewBranch();
		}

		final List<IBranch> allEntities = branchService.getAll();

		for (final IBranch entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getRegion());
			assertNotNull(entityFromDb.getAdress());
			assertNotNull(entityFromDb.getTelephone());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IBranch entity = saveNewBranch();
		branchService.delete(entity.getId());
		assertNull(branchService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewBranch();
		branchService.deleteAll();
		assertEquals(0, branchService.getAll().size());
	}

	@Test
	public void testGetCount() {

		final IBranch entity1 = saveNewBranch();
		branchService.save(entity1);

		final BranchFilter filter = new BranchFilter();
		final long count1 = branchService.getCount(filter);

		final IBranch entity2 = saveNewBranch();
		branchService.save(entity2);

		long count2 = branchService.getCount(filter);

		assertEquals(count1, count2 - 1);
	}

	@Test
	public void testFind() {

		final IBranch entity1 = saveNewBranch();
		branchService.save(entity1);

		final BranchFilter filter = new BranchFilter();
		branchService.find(filter);

	}

}
