package com.itacademy.jd2.raa.beltelecom.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.filter.AttributeValueFilter;

public class AttributeValueTest extends AbstractTest {

	@Test
	public void testCreate() {

		final IAttributeValue entity = saveNewAttributeValue();
		final IAttributeValue entityFromDb = attributeValueService.get(entity.getId());
		assertNotNull(entityFromDb);
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getValue());

		assertNotNull(entityFromDb.getAttribute());

		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));

	}

	@Test
	public void testUpdate() throws InterruptedException {

		final IAttributeValue entity = saveNewAttributeValue();
		Integer newValue = entity.getValue() + 1;
		String newName = entity.getAttribute().getName() + "_updated";
		IAttribute attribute = entity.getAttribute();

		attribute.setName(newName);
		attributeService.save(attribute);

		entity.setAttribute(attribute);
		entity.setValue(newValue);

		Thread.sleep(2000);
		attributeValueService.save(entity);

	}

	@Test
	public void testDelete() {
		final IAttributeValue entity = saveNewAttributeValue();
		attributeValueService.delete(entity.getId());
		assertNull(attributeValueService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewAttributeValue();
		attributeValueService.deleteAll();
		attributeService.deleteAll();
		assertEquals(0, attributeValueService.getAll().size());
		assertEquals(0, attributeService.getAll().size());
	}

	@Test
	public void testGetAll() {
		final int intialCount = attributeValueService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewAttributeValue();
		}

		final List<IAttributeValue> allEntities = attributeValueService.getAll();

		for (final IAttributeValue entityFromDb : allEntities) {

			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getAttribute());
			assertNotNull(entityFromDb.getValue());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testGetCount() {

		final IAttributeValue entity1 = saveNewAttributeValue();
		attributeValueService.save(entity1);

		final AttributeValueFilter filter = new AttributeValueFilter();
		final long count1 = attributeValueService.getCount(filter);

		final IAttributeValue entity2 = saveNewAttributeValue();
		attributeValueService.save(entity2);

		long count2 = attributeValueService.getCount(filter);

		assertEquals(count1, count2 - 1);
	}

	@Test
	public void testFind() {

		final IAttributeValue entity1 = saveNewAttributeValue();
		attributeValueService.save(entity1);

		final AttributeValueFilter filter = new AttributeValueFilter();
		attributeValueService.find(filter);

	}
}
