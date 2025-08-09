package com.mobilnet.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RucDTOUpdate {
    private Integer registrationId;

    // Main contact information
    @NotBlank(message = "El nombre del contacto principal es obligatorio")
    private String mainContactName;

    @Pattern(regexp = "\\d{8}", message = "El DNI del contacto debe tener 8 dígitos numéricos")
    private String mainContactDni;

    @Email(message = "El correo del contacto no es válido")
    private String mainContactEmail;

    @Pattern(regexp = "\\d{9}", message = "El teléfono del contacto debe tener 9 dígitos")
    private String mainContactPhone;

    // Secondary contact information
    @NotBlank(message = "El nombre del contacto secundario es obligatorio")
    private String secondaryContactName;

    @Pattern(regexp = "\\d{8}", message = "El DNI del contacto secundario debe tener 8 dígitos numéricos")
    private String secondaryContactDni;

    @Email(message = "El correo del contacto secundario no es válido")
    private String secondaryContactEmail;

    @Pattern(regexp = "\\d{9}", message = "El teléfono del contacto secundario debe tener 9 dígitos")
    private String secondaryContactPhone;

    // Schedule information
    @NotBlank(message = "El rango de instalación es obligatorio")
    private String installationRange;

    @NotBlank(message = "El lugar de instalación es obligatorio")
    private String installationPlace;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate installationDate;

    // Registration data
    @Min(value = 1, message = "Debe seleccionar un consultor")
    private Integer consultantUserId;

    @Min(value = 1, message = "Debe seleccionar un supervisor")
    private Integer supervisorUserId;

    @Min(value = 1, message = "Debe seleccionar un plan")
    private Integer planId;

    private Integer promotionId;

    private Integer districtId;

    private String observation;

    // Client information
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos numéricos")
    private String clientDni;

    @Pattern(regexp = "\\d{11}", message = "El RUC debe tener 11 dígitos numéricos")
    private String clientRuc;

    @NotBlank(message = "El nombre es obligatorio")
    private String clientFirstName;

    @NotBlank(message = "El apellido es obligatorio")
    private String clientLastName;

    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String clientPhone;

    private Integer statusId;

    private String requestId;

    private String installationId;

    private String cartId;
}
