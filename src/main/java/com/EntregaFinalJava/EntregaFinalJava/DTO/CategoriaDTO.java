package com.EntregaFinalJava.EntregaFinalJava.DTO;

import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CategoriaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<AutoDTO> autos;

    public CategoriaDTO() {
        this.autos = new ArrayList<>();
    }

    public CategoriaDTO(Long id, String nombre, String descripcion, List<AutoDTO> autos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autos = autos;
    }

}