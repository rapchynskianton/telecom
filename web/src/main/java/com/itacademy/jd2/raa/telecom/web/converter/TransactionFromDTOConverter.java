package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserCabinet;
import com.itacademy.jd2.raa.telecom.service.ITransactionService;
import com.itacademy.jd2.raa.telecom.service.IUserCabinetService;
import com.itacademy.jd2.raa.telecom.web.dto.TransactionDTO;

@Component
public class TransactionFromDTOConverter implements Function<TransactionDTO, ITransaction> {

	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private IUserCabinetService userCabinetService;

	@Override
	public ITransaction apply(final TransactionDTO dto) {
		final ITransaction entity = transactionService.createEntity();
		entity.setId(dto.getId());
		entity.setValue(dto.getValue());

		final IUserCabinet userCabinet = userCabinetService.createEntity();
		userCabinet.setId(dto.getUserCabinetId());
		entity.setUserCabinet(userCabinet);

		entity.setDescription(dto.getDescription());
		return entity;
	}
}
