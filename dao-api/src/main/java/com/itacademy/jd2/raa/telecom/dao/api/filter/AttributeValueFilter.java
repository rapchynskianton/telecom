package com.itacademy.jd2.raa.telecom.dao.api.filter;

public class AttributeValueFilter extends AbstractFilter {
	private Integer id;
	
	private boolean fetchAttribute;

	public boolean isFetchAttribute() {
		return fetchAttribute;
	}

	public void setFetchAttribute(boolean fetchAttribute) {
		this.fetchAttribute = fetchAttribute;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
