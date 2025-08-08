package com.mobilnet.service;

import java.util.List;

import com.mobilnet.dto.PromocionFilter;
import com.mobilnet.model.Promocion;
import com.mobilnet.utils.ResultadoResponse;

public interface PromocionService {
    ResultadoResponse crearPromocion(Promocion promocion);
    List<Promocion> listarPromociones();
    List<Promocion> listarFiltros(PromocionFilter filtro);
    Promocion buscarPorId(Integer idPromocion);
    ResultadoResponse actualizarPromocion(Promocion promocion);
    ResultadoResponse cambiarEstado(Integer idPromocion);
}
