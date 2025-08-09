package com.mobilnet.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "promotions")
@Data
@NoArgsConstructor
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	@NotBlank(message = "La descripción es obligatoria")
	@Size(max = 100, message = "La descripción no debe exceder los 100 caracteres")
	private String description;

	@Column(nullable = false)
	private Boolean isActive;
}
