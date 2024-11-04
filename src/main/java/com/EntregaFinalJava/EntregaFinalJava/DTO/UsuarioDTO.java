package com.EntregaFinalJava.EntregaFinalJava.DTO;

import lombok.Builder;
import lombok.Data;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;

@Data
@Builder
public class UsuarioDTO {
    @Schema(description = "Id de usuario", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Nombre del usuario: ", example = "Juan Pérez")
    private String nombre;
    @Schema(description = "email de usuario: ", example = "JuanPerez@email.com")
    private String email;
    @Schema(description = "Teléfono de usuario: ", example = "1-770-736-8031")
    private String telefono;
    @Schema(description = "Lista de autos del usuario", accessMode = Schema.AccessMode.READ_ONLY)
    private List<AutoDTO> autos;

    public UsuarioDTO() {
        this.autos = new ArrayList<>();
    }

    public UsuarioDTO(Long id, String nombre, String email, String telefono, List<AutoDTO> autos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.autos = autos;
    }
}
