package com.sistema.gpon.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    private LocalDate fechaProgramada;
}
