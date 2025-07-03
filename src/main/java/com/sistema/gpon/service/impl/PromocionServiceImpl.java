package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.dto.PlanFilter;
import com.sistema.gpon.dto.PromocionFilter;
import com.sistema.gpon.model.Cliente;
import com.sistema.gpon.model.Plan;
import com.sistema.gpon.service.PromocionService;
import com.sistema.gpon.utils.ResultadoResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.gpon.model.Promocion;
import com.sistema.gpon.repository.PromocionRepository;
import org.springframework.stereotype.Service;

@Service
public class PromocionServiceImpl implements PromocionService {
	
	@Autowired
	private PromocionRepository promocionRepository;

	@Override
	public ResultadoResponse crearPromocion(Promocion promocion) {		
		try {	
			
			if (promocion.getActivo() == null || promocion.getActivo() == false) {
				promocion.setActivo(true);
	        }
			
			Promocion registrada = promocionRepository.save(promocion);
			
			String mensaje = String.format("Promocion nueva registrada correctamente", registrada);		
			return new ResultadoResponse(true, mensaje);
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Error al registrar: " + ex.getMessage());
		}		
	}

	@Override
	public List<Promocion> listarPromociones() {
		return promocionRepository.findAll();
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

			String mensaje = String.format("Promocion actualizada correctamente", actualizado.getIdPromocion());
			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) { 
			return new ResultadoResponse(false, "Error al actualizar: " + ex.getMessage());
		}		
	}

	@Override
	public ResultadoResponse cambiarEstado(Integer id) {
		Promocion promo = this.buscarPorId(id);
		Boolean accion = promo.getActivo() ? false : true;
		String texto;

		if (accion == true) {
			texto = "ha sido activada";
		} else {
			texto = "ha sido inactivada";
		}

		promo.setActivo(!promo.getActivo());

		try {
			Promocion registrado = promocionRepository.save(promo);

			String mensaje = String.format("Promocion con código: %s %s", registrado.getIdPromocion(), texto);
			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Error al cambiar de estado: " + ex.getMessage());
		}
	}
}
