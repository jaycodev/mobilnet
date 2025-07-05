package com.sistema.gpon.service.impl;

import java.util.List;
import java.util.Optional;

import com.sistema.gpon.dto.ClienteFilter;
import com.sistema.gpon.dto.PlanFilter;
import com.sistema.gpon.model.Cliente;
import com.sistema.gpon.model.Promocion;
import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.service.PlanService;
import com.sistema.gpon.utils.ResultadoResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.gpon.model.Plan;
import com.sistema.gpon.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Override
    public ResultadoResponse crearPlan(Plan plan) {
        try {
            plan.setActivo(true);

            Plan registrado = planRepository.save(plan);

            String mensaje = String.format("El plan (Cod. %s) ha sido registrado exitosamente.", registrado.getIdPlan());

            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "Ocurrió un error al registrar el plan: " + ex.getMessage());
        }
    }

    @Override
    public List<Plan> listarPlanes() {
        return planRepository.findAllByOrderByIdPlanDesc();
    }

    @Override
    public List<Plan> listarFiltros(PlanFilter filtro) {
        if (Boolean.TRUE.equals(filtro.getMasRegistrado())) {
            return planRepository.findPlanesMasRegistrados();
        } else {
            return planRepository.findAllWithFilter(filtro.getActivo());
        }
    }

    @Override
    public Plan buscarPorId(Integer idPlan) {
        return planRepository.findById(idPlan).orElseThrow();
    }

    @Override
    public ResultadoResponse actualizarPlan(Plan plan) {
        try {
            Plan actualizado = planRepository.save(plan);

            String mensaje = String.format("Los datos del plan (Cod. %s) han sido actualizados correctamente.", actualizado.getIdPlan());

            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            return new ResultadoResponse(false, "Ocurrió un error al actualizar el plan: " + ex.getMessage());
        }
    }

    @Override
    public ResultadoResponse cambiarEstado(Integer idPlan) {
        Plan plan = this.buscarPorId(idPlan);
        boolean accion = !plan.getActivo();

        String texto = accion ? "activado" : "desactivado";

        plan.setActivo(accion);

        try {
            Plan registrado = planRepository.save(plan);

            String mensaje = String.format("El plan (Cod. %s) ha sido %s correctamente.", registrado.getIdPlan(), texto);
            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "Ocurrió un error al cambiar el estado del plan: " + ex.getMessage());
        }
    }
}
