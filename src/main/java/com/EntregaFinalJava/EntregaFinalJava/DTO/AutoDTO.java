package com.EntregaFinalJava.EntregaFinalJava.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutoDTO {
    @Schema(description = "Id del auto", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;
    @Schema(description = "Nombre de la marca: ", example = "Ford")
    private String marca;
    @Schema(description = "Nombre del modelo: ", example = "Focus")
    private String modelo;
    @Schema(description = "Año del auto: ", example = "2018")
    private int anio;
    @Schema(description = "Precio del auto: ", example = "200000")
    private Double precio;
    @Schema(description = "Nombre de categoría a la que pertenece el auto", accessMode = Schema.AccessMode.READ_ONLY)
    private String categoriaNombre;
    @Schema(description = "Id de la categoría: ", example = "1")
    private Long categoriaId;
    @Schema(description = "Nombre del usuario al que pertenece el auto", accessMode = Schema.AccessMode.READ_ONLY)
    private String usuarioNombre;
    @Schema(description = "Id del usuario: ", example = "10")
    private Long usuarioId;

    public AutoDTO() {
    }

    public AutoDTO(int id, String marca, String modelo, int anio, Double precio, String categoriaNombre,
            Long categoriaId, String usuarioNombre, Long usuarioId) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.categoriaNombre = categoriaNombre;
        this.categoriaId = categoriaId;
        this.usuarioNombre = usuarioNombre;
        this.usuarioId = usuarioId;
    }
}
