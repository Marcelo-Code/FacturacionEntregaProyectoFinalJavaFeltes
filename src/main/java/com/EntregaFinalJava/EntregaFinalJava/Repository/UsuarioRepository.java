package com.EntregaFinalJava.EntregaFinalJava.Repository;

import org.springframework.stereotype.Repository;
import com.EntregaFinalJava.EntregaFinalJava.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByNombre(String nombre);

}