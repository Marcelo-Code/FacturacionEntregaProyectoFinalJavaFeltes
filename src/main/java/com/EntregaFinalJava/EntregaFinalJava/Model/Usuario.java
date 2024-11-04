package com.EntregaFinalJava.EntregaFinalJava.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String telefono;

    @OneToMany(mappedBy = "usuario", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = false)
    private List<Auto> autos;

    public Usuario() {
    }

    @Builder
    public Usuario(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public void addAuto(Auto auto) {
        autos.add(auto);
        auto.setUsuario(this);
    }

    public void removeAuto(Auto auto) {
        autos.remove(auto);
        auto.setUsuario(null);
    }

}
