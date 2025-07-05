package com.sistema.gpon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanFilter {
    private String allFiltros;
    
    public Boolean getActivo() {
    	if ("true".equalsIgnoreCase(allFiltros)) {
    		return true;
    	}else if ("false".equalsIgnoreCase(allFiltros)) {
    		return false;
    	}
    	return null;
    }
    
    public Boolean getMasRegistrado() {
    	return "masRegistrado".equalsIgnoreCase(allFiltros);
    }
}

