package com.EntregaFinalJava.EntregaFinalJava.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table(name = "AUTOS")

public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "MARCA")
    private String marca;

    @Column(name = "MODELO")
    private String modelo;

    @Column(name = "ANIO")
    private int anio;

    @Column(name = "PRECIO")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "CATEGORIA_ID", nullable = false)
    private Categoria categoria;

    // Constructor por defecto
    public Auto() {
    }

    @Builder
    public Auto(String marca, String modelo, int anio, Double precio, Categoria categoria, Usuario usuario) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public String getCategoriaNombre() {
        return this.categoria.getNombre();
    }

    public Long getCategoriaId() {
        return this.categoria.getId();
    }

    public String getUsuarioNombre() {
        return this.usuario.getNombre();
    }

    public Long getUsuarioId() {
        return this.usuario.getId();
    }
}
