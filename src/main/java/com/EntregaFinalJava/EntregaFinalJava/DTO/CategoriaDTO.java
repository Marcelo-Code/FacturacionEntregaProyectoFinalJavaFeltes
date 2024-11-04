package com.EntregaFinalJava.EntregaFinalJava.DTO;

import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class CategoriaDTO {
    @Schema(description = "Id de categoría", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Nombre de la categoría: ", example = "Convertible")
    private String nombre;
    @Schema(description = "Descripción de la categoría: ", example = "Techo retráctil, ideal para disfrutar paseos al aire libre.")
    private String descripcion;
    @Schema(description = "Lista de autos de la categoría", accessMode = Schema.AccessMode.READ_ONLY)
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