package com.mobilnet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Promocion")
@Data
@NoArgsConstructor
public class Promocion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPromocion")
	private Integer idPromocion;

	@Column(name = "Descripcion", nullable = false)
	@NotBlank(message = "La descripción es obligatoria")
	@Size(max = 100, message = "La descripción no debe exceder los 100 caracteres")
	private String descripcion;

	@Column (name = "Activo", nullable = false)
	private Boolean activo;
}
