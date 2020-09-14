package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.web.dto.TransactionDTO;

@Component
public class TransactionToDTOConverter implements Function<ITransaction, TransactionDTO> {

	@Override
	public TransactionDTO apply(final ITransaction entity) {

		final TransactionDTO dto = new TransactionDTO();
		dto.setId(entity.getId());
		dto.setValue(entity.getValue());

		final IUserCabinet userCabinet = entity.getUserCabinet();
		if (userCabinet != null) {
			dto.setUserCabinetId(userCabinet.getId());
		}
		dto.setDescription(entity.getDescription());

		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		return dto;
	}

}
