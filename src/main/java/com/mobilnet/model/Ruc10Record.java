package com.mobilnet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ruc10_records")
public class Ruc10Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "consultant_user_id", nullable = false)
    private User consultantUser;

    @ManyToOne
    @JoinColumn(name = "supervisor_user_id", nullable = false)
    private User supervisorUser;

    @ManyToOne
    @JoinColumn(name = "client_dni", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "main_contact_id", nullable = false)
    private MainContact mainContact;

    @ManyToOne
    @JoinColumn(name = "secondary_contact_id", nullable = false)
    private SecondaryContact secondaryContact;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private RecordState state;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Column(name = "request_id")
    private String requestId = "ninguno";

    @Column(name = "installation_id")
    private String installationId = "ninguno";

    @Column(name = "cart_id")
    private String cartId = "ninguno";

    @Column(name = "observation")
    private String observation;
}
