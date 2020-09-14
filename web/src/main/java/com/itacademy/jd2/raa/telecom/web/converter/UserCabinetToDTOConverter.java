package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.web.dto.UserCabinetDTO;

@Component
public class UserCabinetToDTOConverter implements Function<IUserCabinet, UserCabinetDTO> {

	@Override
	public UserCabinetDTO apply(final IUserCabinet entity) {
		final UserCabinetDTO dto = new UserCabinetDTO();
		dto.setId(entity.getId());
		dto.setActivationDate(entity.getActivationDate());
		dto.setStatus(entity.getStatus());

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			dto.setUserAccountId(userAccount.getId());
			dto.setUserAccountEmail(userAccount.getEmail());
		}

		final IBranch branch = entity.getBranch();
		if (branch != null) {
			dto.setBranchId(branch.getId());
			dto.setBranchRegion(branch.getRegion());
		}

		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}
}
