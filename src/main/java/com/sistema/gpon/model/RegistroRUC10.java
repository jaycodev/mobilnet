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
	@JoinColumn(name = "idEstado", nullable = false)
	private EstadoRegistro estado;

	@Column(name = "IdSolicitud")
	private String idSolicitud;

	@Column(name = "IdInstalacion")
	private String idInstalacion;

	@Column(name = "IdCarrito")
	private String idCarrito;

	@Column(name = "Observacion")
	private String observacion;


}
