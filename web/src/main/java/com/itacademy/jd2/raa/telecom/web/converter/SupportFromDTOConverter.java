package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.service.ISupportService;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;
import com.itacademy.jd2.raa.telecom.web.dto.SupportDTO;

@Component
public class SupportFromDTOConverter implements Function<SupportDTO, ISupport> {

	@Autowired
	private ISupportService supportService;
	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IUserCabinetService userCabinetService;

	@Override
	public ISupport apply(final SupportDTO dto) {

		final ISupport entity = supportService.createEntity();
		entity.setId(dto.getId());
		entity.setProblemName(dto.getProblemName());
		entity.setProblem(dto.getProblem());
		entity.setStatus(dto.getStatus());

		final IUserCabinet userCabinet = userCabinetService.createEntity();
		userCabinet.setId(dto.getUserCabinetId());
		entity.setUserCabinet(userCabinet);

		final IUserAccount manager = userAccountService.createEntity();
		manager.setId(dto.getUserAccountId());
		entity.setUserAccount(manager);

		return entity;
	}
}
