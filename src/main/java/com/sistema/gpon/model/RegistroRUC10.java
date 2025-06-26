package com.sistema.gpon.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroRUC10 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRegistro;

	@ManyToOne
	@JoinColumn(name = "idUsuarioConsulto", nullable = false)
	private Usuario usuarioConsulto;

	@ManyToOne
	@JoinColumn(name = "idUsuarioSupervisor", nullable = false)
	private Usuario usuarioSupervisor;

	@ManyToOne
	@JoinColumn(name = "dniCliente", referencedColumnName = "dniCliente", nullable = false)
	private Cliente cliente;

	private LocalDateTime fechaRegistro;

	@Column(nullable = false, length = 11)
	private String ruc;

	@ManyToOne
	@JoinColumn(name = "idContactoPrincipal", nullable = false)
	private ContactoPrincipal contactoPrincipal;

	@ManyToOne
	@JoinColumn(name = "idContactoSecundario", nullable = false)
	private ContactoSecundario contactoSecundario;

	@ManyToOne
	@JoinColumn(name = "idPlan", nullable = false)
	private Plan plan;

	@ManyToOne
	@JoinColumn(name = "idPromocion")
	private Promocion promocion;

	@ManyToOne
	@JoinColumn(name = "idCronograma", nullable = false)
	private Cronograma cronograma;

	@ManyToOne
	@JoinColumn(name = "idDistrito", nullable = false)
	private Distrito distrito;

	@ManyToOne
	@JoinColumn(name = "idSector", nullable = false)
	private Sector sector;

	@ManyToOne
	@JoinColumn(name = "idEstado", nullable = false)
	private EstadoRegistro estado;

	@Column(nullable = false)
	private String idSolicitud;

	@Column(nullable = false)
	private String idInstalacion;

	@Column(nullable = false)
	private String idCarrito;

	private String observacion;

	@PrePersist
	public void setDefaultFechaRegistro() {
		if (fechaRegistro == null) {
			fechaRegistro = LocalDateTime.now();
		}
	}
}
