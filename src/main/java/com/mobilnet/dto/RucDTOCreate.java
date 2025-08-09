package com.mobilnet.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RucDTOCreate {

    // Main contact information
    private String mainContactName;
    private String mainContactDni;
    private String mainContactEmail;
    private String mainContactPhone;

    // Secondary contact information
    private String secondaryContactName;
    private String secondaryContactDni;
    private String secondaryContactEmail;
    private String secondaryContactPhone;

    // Schedule information
    private String districtName;
    private String sectorName;
    private String department;
    private String province;
    private String installationRange;
    private String number;
    private String interior;
    private String reference;

    // Registration data
    private Integer consultantUserId;
    private Integer supervisorUserId;
    private Integer planId;
    private Integer promotionId;
    private Integer districtId;
    private String observation;

    // Client information
    private String clientDni;
    private String clientRuc;
    private String clientFirstName;
    private String clientLastName;
    private String clientPhone;
}
