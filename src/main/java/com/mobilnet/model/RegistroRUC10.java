package com.mobilnet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroRUC10 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdRegistro")
	private Integer idRegistro;

	@ManyToOne
	@JoinColumn(name = "IdUsuarioConsultor", nullable = false)
	private Usuario usuarioConsultor;

	@ManyToOne
	@JoinColumn(name = "IdUsuarioSupervisor", nullable = false)
	private Usuario usuarioSupervisor;

	@ManyToOne
	@JoinColumn(name = "DniCliente", nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "IdContactoPrincipal", nullable = false)
	private ContactoPrincipal contactoPrincipal;

	@ManyToOne
	@JoinColumn(name = "IdContactoSecundario", nullable = false)
	private ContactoSecundario contactoSecundario;

	@ManyToOne
	@JoinColumn(name = "IdPlan", nullable = false)
	private Plan plan;

	@ManyToOne
	@JoinColumn(name = "IdPromocion")
	private Promocion promocion;

	@ManyToOne
	@JoinColumn(name = "IdCronograma")
	private Cronograma cronograma;

	@ManyToOne
	@JoinColumn(name = "IdEstado")
	private EstadoRegistro estado;

	@ManyToOne
	@JoinColumn(name = "IdDistrito")
	private Distrito distrito;

	@Column(name = "IdSolicitud")
	private String idSolicitud = "ninguno";

	@Column(name = "IdInstalacion")
	private String idInstalacion = "ninguno";

	@Column(name = "IdCarrito")
	private String idCarrito = "ninguno";

	@Column(name = "Observacion")
	private String observacion;
}
