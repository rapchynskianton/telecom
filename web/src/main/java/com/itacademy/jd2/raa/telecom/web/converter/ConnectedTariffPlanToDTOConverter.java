package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.web.dto.ConnectedTariffPlanDTO;

@Component
public class ConnectedTariffPlanToDTOConverter implements Function<IConnectedTariffPlan, ConnectedTariffPlanDTO> {

	@Override
	public ConnectedTariffPlanDTO apply(final IConnectedTariffPlan entity) {
		final ConnectedTariffPlanDTO dto = new ConnectedTariffPlanDTO();
		dto.setId(entity.getId());

		final IUserCabinet userCabinet = entity.getUserCabinet();
		if (userCabinet != null) {
			dto.setUserCabinetId(userCabinet.getId());
		}

		final ITariffPlan tariffPlan = entity.getTariffPlan();
		if (tariffPlan != null) {
			dto.setTariffPlanId(tariffPlan.getId());
			dto.setTariffPlanName(tariffPlan.getName());
		}

		dto.setActivationDate(entity.getActivationDate());
		dto.setSumCost(entity.getSumCost());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		return dto;
	}

}
