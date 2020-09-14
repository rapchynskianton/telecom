package com.itacademy.jd2.raa.telecom.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IBranch;
import com.itacademy.jd2.raa.telecom.service.IBranchService;
import com.itacademy.jd2.raa.telecom.web.dto.BranchDTO;


@Component
public class BranchFromDTOConverter implements Function<BranchDTO, IBranch> {

	@Autowired
	private IBranchService branchService;

	@Override
	public IBranch apply(final BranchDTO dto) {
		final IBranch entity = branchService.createEntity();
		entity.setId(dto.getId());
		entity.setRegion(dto.getRegion());
		entity.setAdress(dto.getAdress());
		entity.setTelephone(dto.getTelephone());
		return entity;
	}
}
