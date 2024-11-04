package com.EntregaFinalJava.EntregaFinalJava.Model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "CATEGORIA")

public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = false)
    private List<Auto> autos;

    // Constructor por defecto
    public Categoria() {
    }

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
