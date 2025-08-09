package com.mobilnet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanFilter {
	private String allFilters;

	public Boolean getActive() {
		if ("true".equalsIgnoreCase(allFilters)) {
			return true;
		} else if ("false".equalsIgnoreCase(allFilters)) {
			return false;
		}
		return null;
	}

	public Boolean getMostRegistered() {
		return "mostRegistered".equalsIgnoreCase(allFilters);
	}
}
