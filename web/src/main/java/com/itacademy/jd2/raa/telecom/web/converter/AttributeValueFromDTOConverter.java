package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.service.IAttributeService;
import com.itacademy.jd2.raa.telecom.service.IAttributeValueService;
import com.itacademy.jd2.raa.telecom.web.dto.AttributeValueDTO;

@Component
public class AttributeValueFromDTOConverter implements Function<AttributeValueDTO, IAttributeValue> {

	@Autowired
	private IAttributeValueService attributeValueService;
	@Autowired
	private IAttributeService attributeService;

	@Override
	public IAttributeValue apply(final AttributeValueDTO dto) {
		final IAttributeValue entity = attributeValueService.createEntity();
		entity.setId(dto.getId());

		final IAttribute attribute = attributeService.createEntity();
		attribute.setId(dto.getAttributeId());
		entity.setAttribute(attribute);

		entity.setValue(dto.getValue());
		return entity;
	}
}
