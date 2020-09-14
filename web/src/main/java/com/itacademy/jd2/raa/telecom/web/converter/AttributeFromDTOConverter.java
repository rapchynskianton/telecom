package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.service.IAttributeService;
import com.itacademy.jd2.raa.telecom.web.dto.AttributeDTO;

@Component
public class AttributeFromDTOConverter implements Function<AttributeDTO, IAttribute> {

	@Autowired
	private IAttributeService attributeService;

	@Override
	public IAttribute apply(final AttributeDTO dto) {
		final IAttribute entity = attributeService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
