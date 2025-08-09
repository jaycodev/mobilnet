package com.mobilnet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "districts")
@Data
@NoArgsConstructor
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;
}
