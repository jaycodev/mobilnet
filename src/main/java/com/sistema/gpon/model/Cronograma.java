package com.sistema.gpon.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCronograma")
    private Integer idCronograma;

    @Column(name = "UbicacionInstalacion")
    private String ubicacionInstalacion;

    @Column(name = "RangoInstalacion")
    private String rangoInstalacion;

    @Column(name = "FechaRegistro")
    private LocalDateTime fechaRegistro ;

    @Column(name = "FechaInstalacion")
    private LocalDateTime fechaInstalacion ;

    @PrePersist
    public void setFechasPorDefecto() {
        if (fechaRegistro == null) {
            fechaRegistro = LocalDateTime.now();
        }
        if (fechaInstalacion == null) {
            fechaInstalacion = fechaRegistro.plusDays(4);
        }
    }

}
