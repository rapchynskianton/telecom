package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IConnectedTariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITariffPlan;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.service.IConnectedTariffPlanService;
import com.itacademy.jd2.raa.telecom.service.ITariffPlanService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;
import com.itacademy.jd2.raa.telecom.web.dto.ConnectedTariffPlanDTO;

@Component
public class ConnectedTariffPlanFromDTOConverter implements Function<ConnectedTariffPlanDTO, IConnectedTariffPlan> {

	@Autowired
	private IConnectedTariffPlanService connectedTariffPlanService;
	@Autowired
	private ITariffPlanService tariffPlanService;
	@Autowired
	private IUserCabinetService userCabinetService;

	@Override
	public IConnectedTariffPlan apply(final ConnectedTariffPlanDTO dto) {
		final IConnectedTariffPlan entity = connectedTariffPlanService.createEntity();
		entity.setId(dto.getId());

		final IUserCabinet userCabinet = userCabinetService.createEntity();
		userCabinet.setId(dto.getUserCabinetId());
		entity.setUserCabinet(userCabinet);

		final ITariffPlan tariffPlan = tariffPlanService.createEntity();
		tariffPlan.setId(dto.getTariffPlanId());

		entity.setTariffPlan(tariffPlan);
		entity.setActivationDate(dto.getActivationDate());
		entity.setSumCost(dto.getSumCost());

		return entity;
	}
}
