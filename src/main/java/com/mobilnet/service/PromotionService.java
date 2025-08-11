package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.dto.PromotionFilter;
import com.mobilnet.model.Promotion;
import com.mobilnet.repository.PromotionRepository;
import com.mobilnet.utils.ResultResponse;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public ResultResponse create(Promotion promotion) {
        try {
            promotion.setIsActive(true);

            Promotion registered = promotionRepository.save(promotion);

            String message = String.format("La promoción (Cod. %s) ha sido registrada exitosamente.",
                    registered.getId());

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultResponse(false, "Ocurrió un error al registrar la promoción: " + ex.getMessage());
        }
    }

    public List<Promotion> list() {
        return promotionRepository.findAllByOrderByIdDesc();
    }

    public List<Promotion> listWithFilters(PromotionFilter filter) {
        return promotionRepository.findAllWithFilter(filter.getIsActive());
    }

    public Promotion findById(Integer promotionId) {
        return promotionRepository.findById(promotionId).orElseThrow();
    }

    public ResultResponse update(Promotion promotion) {
        try {
            Promotion updated = promotionRepository.save(promotion);

            String message = String.format("Los datos de la promoción (Cod. %s) han sido actualizados correctamente.",
                    updated.getId());

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            return new ResultResponse(false, "Ocurrió un error al actualizar la promoción: " + ex.getMessage());
        }
    }

    public ResultResponse changeStatus(Integer promotionId) {
        Promotion promotion = this.findById(promotionId);
        boolean action = !promotion.getIsActive();

        String actionText = action ? "activada" : "desactivada";

        promotion.setIsActive(action);

        try {
            Promotion registered = promotionRepository.save(promotion);

            String message = String.format("La promoción (Cod. %s) ha sido %s correctamente.",
                    registered.getId(), actionText);
            return new ResultResponse(true, message);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultResponse(false,
                    "Ocurrió un error al cambiar el estado de la promoción: " + ex.getMessage());
        }
    }
}
