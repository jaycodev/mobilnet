package com.mobilnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String installationLocation;

    private String installationRange;

    private LocalDate registrationDate;

    private LocalDate installationDate;

    @PrePersist
    public void setDefaultDates() {
        if (registrationDate == null) {
            registrationDate = LocalDate.now();
        }
        if (installationDate == null) {
            installationDate = registrationDate.plusDays(4);
        }
    }
}
