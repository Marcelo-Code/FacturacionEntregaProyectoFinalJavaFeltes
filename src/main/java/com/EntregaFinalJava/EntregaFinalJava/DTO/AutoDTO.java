package com.EntregaFinalJava.EntregaFinalJava.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AutoDTO {
    private int id;
    private String marca;
    private String modelo;
    private int anio;
    private Double precio;
    private String categoriaNombre;
    private Long categoriaId;
    private String usuarioNombre;
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
