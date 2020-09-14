package com.itacademy.jd2.raa.beltelecom.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.filter.SupportFilter;

public class SupportTest extends AbstractTest {

	@Test
	public void testCreate() {

		final ISupport entity = saveNewSupport();
		final ISupport entityFromDb = supportService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getProblemName());
		assertNotNull(entityFromDb.getProblem());
		assertNotNull(entityFromDb.getUserAccount());
		assertTrue(entityFromDb.getStatus());
		assertNotNull(entityFromDb.getUserCabinet());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ISupport entity = saveNewSupport();

		String newProblemName = getRandomPrefix();
		entity.setProblemName(newProblemName);

		Thread.sleep(2000);
		supportService.save(entity);

		final ISupport entityFromDb = supportService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getProblemName());
		assertNotNull(entityFromDb.getProblem());
		assertNotNull(entityFromDb.getUserAccount());
		assertTrue(entityFromDb.getStatus());
		assertNotNull(entityFromDb.getUserCabinet());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = supportService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewSupport();
		}

		final List<ISupport> allEntities = supportService.getAll();

		for (final ISupport entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getProblemName());
			assertNotNull(entityFromDb.getProblem());
			assertNotNull(entityFromDb.getUserAccount());
			assertTrue(entityFromDb.getStatus());
			assertNotNull(entityFromDb.getUserCabinet());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ISupport entity = saveNewSupport();
		supportService.delete(entity.getId());
		assertNull(supportService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewSupport();
		supportService.deleteAll();
		assertEquals(0, supportService.getAll().size());
	}

	@Test
	public void testGetCount() {

		final ISupport entity1 = saveNewSupport();
		supportService.save(entity1);

		final SupportFilter filter = new SupportFilter();
		final long count1 = supportService.getCount(filter);

		final ISupport entity2 = saveNewSupport();
		supportService.save(entity2);

		long count2 = supportService.getCount(filter);

		assertEquals(count1, count2 - 1);
	}
	@Test
	public void testFind() {

		final ISupport entity1 = saveNewSupport();
		supportService.save(entity1);

		final SupportFilter filter = new SupportFilter();
		supportService.find(filter);

	}

}
