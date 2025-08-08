package com.mobilnet.service;

import java.util.List;

import com.mobilnet.dto.PlanFilter;
import com.mobilnet.model.Plan;
import com.mobilnet.utils.ResultadoResponse;

public interface PlanService {
    ResultadoResponse crearPlan(Plan plan);
    List<Plan> listarPlanes();
    List<Plan> listarFiltros(PlanFilter filtro);
    Plan buscarPorId(Integer idPlan);
    ResultadoResponse actualizarPlan(Plan plan);
    ResultadoResponse cambiarEstado(Integer idPlan);
}
