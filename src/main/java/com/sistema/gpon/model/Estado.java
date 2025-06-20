package com.sistema.gpon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EstadoRegistro")
@Data
@NoArgsConstructor
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEstado")
    private int IdEstado;
    @Column(name = "Descripcion")
    private String Descripcion;
}
