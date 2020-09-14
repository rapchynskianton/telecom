package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.enums.UserRole;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;
import com.itacademy.jd2.raa.telecom.web.dto.UserAccountDTO;

@Component
public class UserAccountFromDTOConverter implements Function<UserAccountDTO, IUserAccount> {

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IUserAccount apply(final UserAccountDTO dto) {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity.setUserPassword(dto.getUserPassword());
		entity.setRole(UserRole.valueOf(dto.getRole()));
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setFathersName(dto.getFathersName());
		entity.setAdress(dto.getAdress());
		entity.setTelephone(dto.getTelephone());

		final IPassportDetails infoEntity = userAccountService.createPassportDetailsEntity();
		infoEntity.setId(dto.getId());
		infoEntity.setSerial(dto.getInfo().getSerial());
		infoEntity.setSerialNumber(dto.getInfo().getSerialNumber());
		infoEntity.setDateOfIssue(dto.getInfo().getDateOfIssue());
		infoEntity.setIdentificationNumber(dto.getInfo().getIdentificationNumber());
		infoEntity.setPassportAuthority(dto.getInfo().getPassportAuthority());

		entity.setPassportDetails(infoEntity);

		return entity;
	}
}
