package com.EntregaFinalJava.EntregaFinalJava.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "USUARIOS_API")
public class UsuarioApi {
    @Id
    private Long id;
    private String name;
    private String email;
    private String phone;

    // Constructor por defecto
    public UsuarioApi() {
    }

    public UsuarioApi(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}