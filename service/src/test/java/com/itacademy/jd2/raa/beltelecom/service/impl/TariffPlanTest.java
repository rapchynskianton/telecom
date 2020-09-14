package com.itacademy.jd2.raa.beltelecom.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.TariffPlanFilter;

public class TariffPlanTest extends AbstractTest {

	@Test
	public void testCreate() {

		final ITariffPlan entity = saveNewTariffPlan();
		final ITariffPlan entityFromDb = tariffPlanService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ITariffPlan entity = saveNewTariffPlan();

		String newName = getRandomPrefix();
		entity.setName(newName);

		Thread.sleep(2000);
		tariffPlanService.save(entity);

		final ITariffPlan entityFromDb = tariffPlanService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());

		assertNotNull(entityFromDb.getName());

		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());

	}

	@Test
	public void testGetAll() {
		final int intialCount = tariffPlanService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewTariffPlan();
		}

		final List<ITariffPlan> allEntities = tariffPlanService.getAll();

		for (final ITariffPlan entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());

			assertNotNull(entityFromDb.getName());

			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final ITariffPlan entity = saveNewTariffPlan();
		tariffPlanService.delete(entity.getId());
		assertNull(tariffPlanService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewTariffPlan();
		tariffPlanService.deleteAll();
		assertEquals(0, tariffPlanService.getAll().size());
	}

	@Test
	public void testGetCount() {

		final ITariffPlan entity1 = saveNewTariffPlan();
		tariffPlanService.save(entity1);

		final TariffPlanFilter filter = new TariffPlanFilter();
		final long count1 = tariffPlanService.getCount(filter);

		final ITariffPlan entity2 = saveNewTariffPlan();
		tariffPlanService.save(entity2);

		long count2 = tariffPlanService.getCount(filter);

		assertEquals(count1, count2 - 1);
	}
		@Test
	public void testFind() {

		final ITariffPlan entity1 = saveNewTariffPlan();
		tariffPlanService.save(entity1);

		final TariffPlanFilter filter = new TariffPlanFilter();
		tariffPlanService.find(filter);

	}
}
