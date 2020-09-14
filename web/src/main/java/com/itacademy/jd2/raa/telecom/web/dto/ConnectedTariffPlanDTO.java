package com.itacademy.jd2.raa.telecom.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class ConnectedTariffPlanDTO {
	private Integer id;

	@NotNull
	private Integer userCabinetId;

	@NotNull
	private Integer tariffPlanId;

	private String tariffPlanName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date activationDate;
	@NotNull

	private BigDecimal sumCost;

	private Date created;

	private Date updated;

	public Integer getUserCabinetId() {
		return userCabinetId;
	}

	public void setUserCabinetId(Integer userCabinetId) {
		this.userCabinetId = userCabinetId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTariffPlanId() {
		return tariffPlanId;
	}

	public void setTariffPlanId(Integer tariffPlanId) {
		this.tariffPlanId = tariffPlanId;
	}

	public String getTariffPlanName() {
		return tariffPlanName;
	}

	public void setTariffPlanName(String tariffPlanName) {
		this.tariffPlanName = tariffPlanName;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date ativationDate) {
		this.activationDate = ativationDate;
	}

	public BigDecimal getSumCost() {
		return sumCost;
	}

	public void setSumCost(BigDecimal sumCost) {
		this.sumCost = sumCost;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
