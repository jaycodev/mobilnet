package com.mobilnet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "main_contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 8)
    private String dni;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 9)
    private String phone;
}
