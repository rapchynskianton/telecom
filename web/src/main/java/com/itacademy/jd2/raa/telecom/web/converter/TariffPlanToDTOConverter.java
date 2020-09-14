package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.web.dto.TariffPlanDTO;

@Component
public class TariffPlanToDTOConverter implements Function<ITariffPlan, TariffPlanDTO> {
	private static final Logger LOGGER = LoggerFactory.getLogger(TariffPlanToDTOConverter.class);

	@Override
	public TariffPlanDTO apply(final ITariffPlan entity) {
		final TariffPlanDTO modelDto = new TariffPlanDTO();
		modelDto.setId(entity.getId());
		modelDto.setName(entity.getName());
		modelDto.setCostPerUnit(entity.getCostPerUnit());
		modelDto.setUnit(entity.getUnit());
		modelDto.setType(entity.getType());
		modelDto.setCreated(entity.getCreated());
		modelDto.setUpdated(entity.getUpdated());

		try {
			final Set<IAttributeValue> attributeValue = entity.getAttributeValue();
			if (attributeValue != null) {
				modelDto.setAttributeValueIds(
						attributeValue.stream().map(IAttributeValue::getId).collect(Collectors.toSet()));
			}
		} catch (final Exception e) {
			LOGGER.warn("ignore conversion of 'Attribute Value' collection because of:" + e.getMessage());
		}

		return modelDto;
	}
}
