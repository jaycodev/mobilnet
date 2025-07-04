package com.sistema.gpon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanFilter {

    
    private String allFiltros; /*se usara para evaluar los distitnso filtrados en la lista */
    
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


    public String getAllFiltros() {
        return allFiltros;
    }

    public void setAllFiltros(String allFiltros) {
        this.allFiltros = allFiltros;
    }
}

