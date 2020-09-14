package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IPassportDetails;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.web.dto.UserAccountDTO;

@Component
public class UserAccountToDTOConverter implements Function<IUserAccount, UserAccountDTO> {

	@Override
	public UserAccountDTO apply(final IUserAccount entity) {
		final UserAccountDTO dto = new UserAccountDTO();

		dto.setId(entity.getId());
		dto.setEmail(entity.getEmail());
		dto.setUserPassword(entity.getUserPassword());
		dto.setRole(entity.getRole().name());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setFathersName(entity.getFathersName());
		dto.setAdress(entity.getAdress());
		dto.setTelephone(entity.getTelephone());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		final IPassportDetails passportDetails = entity.getPassportDetails();
		if (passportDetails != null) {

			dto.getInfo().setSerial(passportDetails.getSerial());
			dto.getInfo().setSerialNumber(passportDetails.getSerialNumber());
			dto.getInfo().setDateOfIssue(passportDetails.getDateOfIssue());
			dto.getInfo().setIdentificationNumber(passportDetails.getIdentificationNumber());
			dto.getInfo().setPassportAuthority(passportDetails.getPassportAuthority());
		}

		return dto;
	}

}
