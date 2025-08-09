package com.mobilnet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "record_states")
@Data
@NoArgsConstructor
public class RecordState {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String description;
}
