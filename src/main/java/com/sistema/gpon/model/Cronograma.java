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
    private Integer idCronograma;

    private String ubicacionInstalacion;

    private String rangoInstalacion;

    private LocalDateTime fechaRegistro ;
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
