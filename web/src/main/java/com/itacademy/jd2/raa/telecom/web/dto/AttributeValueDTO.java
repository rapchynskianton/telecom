package com.itacademy.jd2.raa.telecom.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AttributeValueDTO {

	private Integer id;

	@NotNull
	private Integer attributeId;

	private String attributeName;
	
	@NotNull
	private Integer value;

	private Date created;

	private Date updated;

	
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
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
