package com.mobilnet.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserProfileDTO {
    private String firstName;
    private String lastName;

    @Column(name = "Correo", nullable = false, unique = true)
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    @Size(max = 100, message = "El correo no debe superar los 100 caracteres")
    private String email;
}
