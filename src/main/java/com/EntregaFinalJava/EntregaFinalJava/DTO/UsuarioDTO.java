package com.EntregaFinalJava.EntregaFinalJava.DTO;

import lombok.Builder;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
@Builder
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
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
