package com.EntregaFinalJava.EntregaFinalJava.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.EntregaFinalJava.EntregaFinalJava.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("SELECT c FROM Categoria c")
    List<Categoria> getCategories();

    Optional<Categoria> findById(Long id);
}