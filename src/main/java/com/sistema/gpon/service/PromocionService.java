package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.dto.PromocionFilter;
import com.sistema.gpon.model.Promocion;
import com.sistema.gpon.utils.ResultadoResponse;

public interface PromocionService {
    ResultadoResponse crearPromocion(Promocion promocion);
    List<Promocion> listarPromociones();
    List<Promocion> listarFiltros(PromocionFilter filtro);
    Promocion buscarPorId(Integer idPromocion);
    ResultadoResponse actualizarPromocion(Promocion promocion);
    ResultadoResponse cambiarEstado(Integer idPromocion);
}
