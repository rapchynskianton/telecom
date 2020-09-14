package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ISupport;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.web.dto.SupportDTO;

@Component
public class SupportToDTOConverter implements Function<ISupport, SupportDTO> {

	@Override
	public SupportDTO apply(final ISupport entity) {

		final SupportDTO dto = new SupportDTO();
		dto.setId(entity.getId());
		dto.setProblemName(entity.getProblemName());
		dto.setProblem(entity.getProblem());
		dto.setStatus(entity.getStatus());

		final IUserAccount manager = entity.getUserAccount();
		if (manager != null) {
			dto.setUserAccountId(manager.getId());
			dto.setUserAccountEmail(manager.getEmail());
		}

		final IUserCabinet userCabinet = entity.getUserCabinet();
		if (userCabinet != null) {
			dto.setUserCabinetId(userCabinet.getId());
		}

		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		return dto;
	}

}
