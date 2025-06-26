package com.sistema.gpon.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactoSecundario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContactoSecundario;

    @Column(nullable = false, length = 30)
    private String nombreContacto;

    @Column(nullable = false, length = 8)
    private String dni;

    @Column(nullable = false, length = 30)
    private String correo;

    @Column(nullable = false, length = 9)
    private String telefono;
}
