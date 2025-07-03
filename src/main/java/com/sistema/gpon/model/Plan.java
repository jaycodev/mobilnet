package com.sistema.gpon.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "Plan")
@Data
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPlan")
	private Integer idPlan;

	@Column(name = "Descripcion", nullable = false)
	@NotBlank(message = "La descripción es obligatoria")
	@Size(max = 100, message = "La descripción no debe exceder los 100 caracteres")
	private String descripcion;

	@Column (name = "Activo", nullable = false)
	private Boolean activo;
}
