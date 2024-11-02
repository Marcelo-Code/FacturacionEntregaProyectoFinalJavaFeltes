package com.EntregaFinalJava.EntregaFinalJava.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.EntregaFinalJava.EntregaFinalJava.DTO.AutoDTO;
import com.EntregaFinalJava.EntregaFinalJava.Model.Auto;
import com.EntregaFinalJava.EntregaFinalJava.Model.Usuario;
import com.EntregaFinalJava.EntregaFinalJava.Service.CategoriaService;
import com.EntregaFinalJava.EntregaFinalJava.Service.UsuarioService;

@Component
public class AutoMapper {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    @Lazy
    UsuarioService usuarioService;

    public AutoDTO toAutoDTO(Auto car) {
        if (car == null) {
            return null;
        }

        return AutoDTO.builder()
                .id(car.getId())
                .marca(car.getMarca())
                .modelo(car.getModelo())
                .anio(car.getAnio())
                .precio(car.getPrecio())
                .categoriaNombre(car.getCategoriaNombre())
                .categoriaId(car.getCategoriaId())
                .usuarioNombre(car.getUsuarioNombre())
                .usuarioId(car.getUsuarioId())
                .build();
    }

    public Auto toAuto(AutoDTO carDto) {
        if (carDto == null) {
            return null;
        }
        Auto car = new Auto();
        car.setMarca(carDto.getMarca());
        car.setModelo(carDto.getModelo());
        car.setAnio(carDto.getAnio());
        car.setPrecio(carDto.getPrecio());
        car.setCategoria(categoriaService.getCategoria(carDto.getCategoriaId()));

        Usuario usuario = usuarioService.getUserByID(carDto.getUsuarioId());

        car.setUsuario(usuario);

        return car;
    }

}
