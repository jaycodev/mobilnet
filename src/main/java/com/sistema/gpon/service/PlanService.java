package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Plan;
import com.sistema.gpon.utils.ResultadoResponse;

public interface PlanService {
    ResultadoResponse crearPlan(Plan plan);
    List<Plan> listarPlanes();
    Plan buscarPorId(Integer idPlan);
    ResultadoResponse actualizarPlan(Plan plan);
    ResultadoResponse cambiarEstado(Integer idPlan);
}
