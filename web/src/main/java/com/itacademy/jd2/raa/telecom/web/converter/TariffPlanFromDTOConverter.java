package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IAttributeValue;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.service.IAttributeValueService;
import com.itacademy.jd2.raa.telecom.service.ITariffPlanService;
import com.itacademy.jd2.raa.telecom.web.dto.TariffPlanDTO;

@Component
public class TariffPlanFromDTOConverter implements Function<TariffPlanDTO, ITariffPlan> {

	@Autowired
	private ITariffPlanService tariffPlanService;

	@Autowired
	private IAttributeValueService attributeValueService;

	@Override
	public ITariffPlan apply(final TariffPlanDTO dto) {
		final ITariffPlan entity = tariffPlanService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCostPerUnit(dto.getCostPerUnit());
		entity.setUnit(dto.getUnit());
		entity.setType(dto.getType());
		
		
		final Set<Integer> attributeValueIds = dto.getAttributeValueIds();
		attributeValueIds.add(dto.getValueAttribute());
		if (CollectionUtils.isNotEmpty(attributeValueIds)) {
			entity.setAttributeValue(attributeValueIds.stream().map((id) -> {
				final IAttributeValue attributeValue = attributeValueService.createEntity();
				attributeValue.setId(id);
				return attributeValue;
			}).collect(Collectors.toSet()));
		}


		return entity;
	}
}
