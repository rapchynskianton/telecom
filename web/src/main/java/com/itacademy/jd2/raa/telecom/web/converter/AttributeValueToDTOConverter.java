package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttribute;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.web.dto.AttributeValueDTO;

@Component
public class AttributeValueToDTOConverter implements Function<IAttributeValue, AttributeValueDTO> {

	@Override
	public AttributeValueDTO apply(final IAttributeValue entity) {
		
		final AttributeValueDTO dto = new AttributeValueDTO();
		dto.setId(entity.getId());

		final IAttribute attribute = entity.getAttribute();
		if (attribute != null) {
			dto.setAttributeId(attribute.getId());
			dto.setAttributeName(attribute.getName());
		}

		dto.setValue(entity.getValue());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		return dto;
	}

}
