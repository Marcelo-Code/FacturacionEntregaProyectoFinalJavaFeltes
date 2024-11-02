package com.EntregaFinalJava.EntregaFinalJava.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.EntregaFinalJava.EntregaFinalJava.DTO.AutoDTO;
import com.EntregaFinalJava.EntregaFinalJava.DTO.CategoriaDTO;
import com.EntregaFinalJava.EntregaFinalJava.Model.Auto;
import com.EntregaFinalJava.EntregaFinalJava.Model.Categoria;

@Component
public class CategoriaMapper {

    @Autowired
    AutoMapper carMapper;

    public CategoriaDTO toCategoriaDTO(Categoria category) {
        if (category == null) {
            return null;
        }

        List<AutoDTO> carDTOs = category.getAutos().stream()
                .map(carMapper::toAutoDTO)
                .collect(Collectors.toList());

        return CategoriaDTO.builder()
                .id(category.getId())
                .nombre(category.getNombre())
                .descripcion(category.getDescripcion())
                .autos(carDTOs)
                .build();
    }

    public Categoria toCategoria(CategoriaDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }

        Categoria category = new Categoria();
        category.setNombre(categoryDTO.getNombre());
        category.setDescripcion(categoryDTO.getDescripcion());

        List<AutoDTO> carDTOs = categoryDTO.getAutos() != null ? categoryDTO.getAutos() : Collections.emptyList();

        List<Auto> convertedCategories = carDTOs.stream().map(carMapper::toAuto)
                .collect(Collectors.toList());

        category.setAutos(convertedCategories);

        return category;
    }

}
