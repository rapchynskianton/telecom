package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.web.dto.BranchDTO;


@Component
public class BranchToDTOConverter implements Function<IBranch, BranchDTO> {

	@Override
	public BranchDTO apply(final IBranch entity) {
		final BranchDTO dto = new BranchDTO();
		dto.setId(entity.getId());
		dto.setRegion(entity.getRegion());
		dto.setAdress(entity.getAdress());
		dto.setTelephone(entity.getTelephone());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}
}
