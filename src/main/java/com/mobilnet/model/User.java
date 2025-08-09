package com.mobilnet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	@NotNull(message = "Debe seleccionar un rol")
	private Role role;

	@Column(nullable = false)
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
	@Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre solo debe contener letras y espacios")
	private String firstName;

	@Column(nullable = false)
	@NotBlank(message = "El apellido es obligatorio")
	@Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
	@Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+$", message = "El apellido solo debe contener letras y espacios")
	private String lastName;

	@Column(nullable = false, unique = true, length = 100)
	@NotBlank(message = "El correo es obligatorio")
	@Email(message = "El correo debe tener un formato válido")
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean isActive;
}
