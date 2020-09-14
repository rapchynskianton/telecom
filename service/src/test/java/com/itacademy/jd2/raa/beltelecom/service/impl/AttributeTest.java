package com.itacademy.jd2.raa.beltelecom.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeFilter;

public class AttributeTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IAttribute entity = saveNewAttribute();
		
		final IAttribute entityFromDb = attributeService.get(entity.getId());
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IAttribute entity = saveNewAttribute();
		String newName = entity.getName() + "_updated";
		entity.setName(newName);

		Thread.sleep(2000);
		attributeService.save(entity);

		final IAttribute entityFromDb = attributeService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getName());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = attributeService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewAttribute();
		}

		final List<IAttribute> allEntities = attributeService.getAll();

		for (final IAttribute entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IAttribute entity = saveNewAttribute();
		attributeService.delete(entity.getId());
		assertNull(attributeService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewAttribute();
		attributeService.deleteAll();
		assertEquals(0, attributeService.getAll().size());
	}

	@Test
	public void testGetCount() {

		final IAttribute entity1 = saveNewAttribute();
		attributeService.save(entity1);

		final AttributeFilter filter = new AttributeFilter();
		final long count1 = attributeService.getCount(filter);

		final IAttribute entity2 = saveNewAttribute();
		attributeService.save(entity2);

		long count2 = attributeService.getCount(filter);

		assertEquals(count1, count2 - 1);
	}

	@Test
	public void testFind() {

		final IAttribute entity1 = saveNewAttribute();
		attributeService.save(entity1);

		final AttributeFilter filter = new AttributeFilter();
		attributeService.find(filter);

	}

}
