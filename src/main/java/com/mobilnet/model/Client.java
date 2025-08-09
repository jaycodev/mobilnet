package com.mobilnet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
public class Client {

	@Id
	@Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos numéricos")
	private String dni;

	@Column(nullable = false)
	@Pattern(regexp = "\\d{11}", message = "El RUC debe tener 11 dígitos numéricos")
	private String ruc;

	@Column(nullable = false)
	@NotBlank(message = "El nombre es obligatorio")
	private String firstName;

	@Column(nullable = false)
	@NotBlank(message = "El apellido es obligatorio")
	private String lastName;

	@Column(nullable = false)
	@Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
	private String phone;

	@Column(nullable = false)
	private Boolean isActive;
}
