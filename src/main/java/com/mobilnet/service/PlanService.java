package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.dto.PlanFilter;
import com.mobilnet.model.Plan;
import com.mobilnet.repository.PlanRepository;
import com.mobilnet.utils.ResultResponse;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public ResultResponse create(Plan plan) {
        try {
            plan.setIsActive(true);

            Plan registered = planRepository.save(plan);

            String message = String.format("El plan (Cod. %s) ha sido registrado exitosamente.",
                    registered.getId());

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultResponse(false, "Ocurrió un error al registrar el plan: " + ex.getMessage());
        }
    }

    public List<Plan> list() {
        return planRepository.findAllByOrderByIdDesc();
    }

    public List<Plan> listWithFilters(PlanFilter filter) {
        if (Boolean.TRUE.equals(filter.getMostRegistered())) {
            return planRepository.findMostRegisteredPlans();
        } else {
            return planRepository.findAllWithFilter(filter.getActive());
        }
    }

    public Plan findById(Integer planId) {
        return planRepository.findById(planId).orElseThrow();
    }

    public ResultResponse update(Plan plan) {
        try {
            Plan updated = planRepository.save(plan);

            String message = String.format("Los datos del plan (Cod. %s) han sido actualizados correctamente.",
                    updated.getId());

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            return new ResultResponse(false, "Ocurrió un error al actualizar el plan: " + ex.getMessage());
        }
    }

    public ResultResponse changeStatus(Integer planId) {
        Plan plan = this.findById(planId);
        boolean action = !plan.getIsActive();

        String actionText = action ? "activado" : "desactivado";

        plan.setIsActive(action);

        try {
            Plan registered = planRepository.save(plan);

            String message = String.format("El plan (Cod. %s) ha sido %s correctamente.", registered.getId(),
                    actionText);
            return new ResultResponse(true, message);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultResponse(false, "Ocurrió un error al cambiar el estado del plan: " + ex.getMessage());
        }
    }
}
