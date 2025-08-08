package com.mobilnet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdUsuario")
	private Integer idUsuario;

	@ManyToOne
	@JoinColumn(name = "IdRol", nullable = false)
	@NotNull(message = "Debe seleccionar un rol")
	private Rol rol;

	@Column(name = "Nombre", nullable = false)
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
	@Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo debe contener letras y espacios")
	private String nombre;

	@Column(name = "Apellido", nullable = false)
	@NotBlank(message = "El apellido es obligatorio")
	@Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
	@Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido solo debe contener letras y espacios")
	private String apellido;

	@Column(name = "Correo", nullable = false, unique = true)
	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "El correo debe tener un formato válido")
	@Size(max = 100, message = "El correo no debe superar los 100 caracteres")
	private String correo;

	@Column(name = "Contrasena", nullable = false)
	private String contrasena;

	@Column(name = "Activo", nullable = false)
	private Boolean activo;
}
