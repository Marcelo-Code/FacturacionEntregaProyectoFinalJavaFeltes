package com.EntregaFinalJava.EntregaFinalJava.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.EntregaFinalJava.EntregaFinalJava.Model.Auto;

@Repository
public interface AutosRepository extends JpaRepository<Auto, Integer> {

    @Query("SELECT a FROM Auto a WHERE a.marca = :marca")
    List<Auto> getCarsByBrand(@Param("marca") String marca);

    @Query("SELECT a FROM Auto a JOIN a.categoria c")
    List<Auto> getCarsMain();

    @Query("SELECT a from Auto a WHERE a.anio > :anio")
    List<Auto> getCarsByYearGreaterThan(@Param("anio") int anio);

    @Query("SELECT a from Auto a WHERE a.anio < :anio")
    List<Auto> getCarsByYearLessThan(@Param("anio") int anio);

    @Modifying
    @Query("DELETE FROM Auto a WHERE a.id = :id")
    void eliminarAutoPorId(@Param("id") int id);

    Optional<Auto> findById(int id);

}