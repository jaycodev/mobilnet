package com.mobilnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.dto.PromocionFilter;
import com.mobilnet.model.Promocion;
import com.mobilnet.repository.PromocionRepository;
import com.mobilnet.service.PromocionService;
import com.mobilnet.utils.ResultadoResponse;

@Service
public class PromocionServiceImpl implements PromocionService {

	@Autowired
	private PromocionRepository promocionRepository;

	@Override
	public ResultadoResponse crearPromocion(Promocion promocion) {
		try {
			promocion.setActivo(true);

			Promocion registrado = promocionRepository.save(promocion);

			String mensaje = String.format("La promoción (Cod. %s) ha sido registrada exitosamente.",
					registrado.getIdPromocion());

			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Ocurrió un error al registrar la promoción: " + ex.getMessage());
		}
	}

	@Override
	public List<Promocion> listarPromociones() {
		return promocionRepository.findAllByOrderByIdPromocionDesc();
	}

	@Override
	public List<Promocion> listarFiltros(PromocionFilter filtro) {
		return promocionRepository.findAllWithFilter(filtro.getActivo());
	}

	@Override
	public Promocion buscarPorId(Integer idPromocion) {
		return promocionRepository.findById(idPromocion).orElseThrow();
	}

	@Override
	public ResultadoResponse actualizarPromocion(Promocion promocion) {
		try {
			Promocion actualizado = promocionRepository.save(promocion);

			String mensaje = String.format("Los datos de la promoción (Cod. %s) han sido actualizados correctamente.",
					actualizado.getIdPromocion());

			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) {
			return new ResultadoResponse(false, "Ocurrió un error al actualizar la promoción: " + ex.getMessage());
		}
	}

	@Override
	public ResultadoResponse cambiarEstado(Integer id) {
		Promocion promocion = this.buscarPorId(id);
		boolean accion = !promocion.getActivo();

		String texto = accion ? "activada" : "desactivada";

		promocion.setActivo(accion);

		try {
			Promocion registrado = promocionRepository.save(promocion);

			String mensaje = String.format("La promoción (Cod. %s) ha sido %s correctamente.",
					registrado.getIdPromocion(), texto);
			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false,
					"Ocurrió un error al cambiar el estado de la promoción: " + ex.getMessage());
		}
	}
}
