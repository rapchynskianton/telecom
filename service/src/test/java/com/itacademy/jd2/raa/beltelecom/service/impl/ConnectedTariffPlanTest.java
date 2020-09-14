package com.itacademy.jd2.raa.beltelecom.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.filter.ConnectedTariffPlanFilter;

public class ConnectedTariffPlanTest extends AbstractTest {
	@Test
	public void testCreate() {

		final IConnectedTariffPlan entity = saveNewConnectedTariffPlan();

		final IConnectedTariffPlan entityFromDb = connectedTariffPlanService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getTariffPlan());
		assertNotNull(entityFromDb.getActivationDate());
		assertNotNull(entityFromDb.getSumCost());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IConnectedTariffPlan entity = saveNewConnectedTariffPlan();
		BigDecimal newSumCost = getRandomDecimal();
		entity.setSumCost(newSumCost);
		Thread.sleep(2000);
		connectedTariffPlanService.save(entity);

		final IConnectedTariffPlan entityFromDb = connectedTariffPlanService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getTariffPlan());
		assertNotNull(entityFromDb.getActivationDate());
		assertNotNull(entityFromDb.getSumCost());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = connectedTariffPlanService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewConnectedTariffPlan();
		}

		final List<IConnectedTariffPlan> allEntities = connectedTariffPlanService.getAll();

		for (final IConnectedTariffPlan entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getTariffPlan());
			assertNotNull(entityFromDb.getActivationDate());
			assertNotNull(entityFromDb.getSumCost());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IConnectedTariffPlan entity = saveNewConnectedTariffPlan();
		connectedTariffPlanService.delete(entity.getId());
		assertNull(connectedTariffPlanService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		connectedTariffPlanService.deleteAll();
		assertEquals(0, connectedTariffPlanService.getAll().size());
	}

	@Test
	public void testGetCount() {

		final IConnectedTariffPlan entity1 = saveNewConnectedTariffPlan();
		connectedTariffPlanService.save(entity1);

		final ConnectedTariffPlanFilter filter = new ConnectedTariffPlanFilter();
		final long count1 = connectedTariffPlanService.getCount(filter);

		final IConnectedTariffPlan entity2 = saveNewConnectedTariffPlan();
		connectedTariffPlanService.save(entity2);

		long count2 = connectedTariffPlanService.getCount(filter);

		assertEquals(count1, count2 - 1);
	}
	@Test
	public void testFind() {

		final IConnectedTariffPlan entity1 = saveNewConnectedTariffPlan();
		connectedTariffPlanService.save(entity1);

		final ConnectedTariffPlanFilter filter = new ConnectedTariffPlanFilter();
		connectedTariffPlanService.find(filter);

	}
}
