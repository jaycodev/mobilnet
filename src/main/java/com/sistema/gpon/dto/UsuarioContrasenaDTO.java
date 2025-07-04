package com.sistema.gpon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioContrasenaDTO {

    @NotBlank(message = "Debe ingresar la contraseña actual")
    private String contrasenaActual;

    @NotBlank(message = "Debe ingresar una nueva contraseña")
    @Size(min = 6, message = "La nueva contraseña debe tener al menos 6 caracteres")
    private String nuevaContrasena;

    @NotBlank(message = "Debe confirmar la nueva contraseña")
    private String confirmarNueva;
}
