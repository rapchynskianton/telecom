package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.web.dto.AttributeDTO;

@Component
public class AttributeToDTOConverter implements Function<IAttribute, AttributeDTO> {

	@Override
	public AttributeDTO apply(final IAttribute entity) {
		final AttributeDTO dto = new AttributeDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}
}
