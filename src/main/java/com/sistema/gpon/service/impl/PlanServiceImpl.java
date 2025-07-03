package com.sistema.gpon.service.impl;

import java.util.List;
import java.util.Optional;

import com.sistema.gpon.model.Promocion;
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
    public ResultadoResponse crearPlan(Plan planObtenido) {
        try {
            Plan planGuardado = planRepository.save(planObtenido);
            String mensaje = String.format("El plan fue creado correctamente con codigo: ", planGuardado.getIdPlan());
            return new ResultadoResponse(true, mensaje);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultadoResponse(false, "Error al crear Plan" + e.getMessage());
        }
    }

    @Override
    public List<Plan> listarPlanes() {
        return planRepository.findAll();
    }

    @Override
    public Plan buscarPorId(Integer idPlan) {
        return planRepository.findById(idPlan).orElseThrow();
    }

    @Override
    public ResultadoResponse actualizarPlan(Plan plan) {
        try {
            Plan planActualizado = planRepository.save(plan);
            String mensaje = String.format("El Plan fue actualizado con codigo", planActualizado.getIdPlan());
            return new ResultadoResponse(true, mensaje);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "El plan no se pudo actualizar" + ex.getLocalizedMessage());
        }
    }

    @Override
    public ResultadoResponse cambiarEstado(Integer idPlan) {
        Plan plan = this.buscarPorId(idPlan);
        Boolean accion = plan.getActivo() ? false : true;
        String texto;

        if (accion == true) {
            texto = "ha sido activado";
        } else {
            texto = "ha sido inactivado";
        }

        plan.setActivo(!plan.getActivo());

        try {
            Plan registrado = planRepository.save(plan);

            String mensaje = String.format("Plan con código: %s %s", registrado.getIdPlan(), texto);
            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "Error al cambiar de estado: " + ex.getMessage());
        }
    }
}
