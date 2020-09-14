package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.service.IBranchService;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;
import com.itacademy.jd2.raa.telecom.web.dto.UserCabinetDTO;

@Component
public class UserCabinetFromDTOConverter implements Function<UserCabinetDTO, IUserCabinet> {

	@Autowired
	private IUserCabinetService userCabinetService;
	@Autowired
	private IUserAccountService userAccountService;
	@Autowired
	private IBranchService branchService;

	@Override
	public IUserCabinet apply(final UserCabinetDTO dto) {
		final IUserCabinet entity = userCabinetService.createEntity();
		entity.setId(dto.getId());
		entity.setActivationDate(dto.getActivationDate());
		entity.setStatus(dto.getStatus());

		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId());
		entity.setUserAccount(userAccount);

		final IBranch branch = branchService.createEntity();
		branch.setId(dto.getBranchId());
		entity.setBranch(branch);

		return entity;
	}
}
