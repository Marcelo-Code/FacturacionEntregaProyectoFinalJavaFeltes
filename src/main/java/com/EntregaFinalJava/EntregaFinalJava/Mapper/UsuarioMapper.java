package com.EntregaFinalJava.EntregaFinalJava.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.EntregaFinalJava.EntregaFinalJava.DTO.AutoDTO;
import com.EntregaFinalJava.EntregaFinalJava.DTO.UsuarioDTO;
import com.EntregaFinalJava.EntregaFinalJava.Model.Auto;
import com.EntregaFinalJava.EntregaFinalJava.Model.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

@Component
public class UsuarioMapper {

    @Autowired
    AutoMapper carMapper;

    public UsuarioDTO toUsuarioDTO(Usuario user) {
        if (user == null) {
            return null;
        }
        List<AutoDTO> autoDTOs = user.getAutos().stream()
                .map(carMapper::toAutoDTO)
                .collect(Collectors.toList());
        return UsuarioDTO.builder()
                .id(user.getId())
                .nombre(user.getNombre())
                .email(user.getEmail())
                .telefono(user.getTelefono())
                .autos(autoDTOs)
                .build();
    }

    public Usuario toUsuario(UsuarioDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        Usuario user = new Usuario();
        user.setNombre(userDTO.getNombre());
        user.setEmail(userDTO.getEmail());
        user.setTelefono(userDTO.getTelefono());
        List<AutoDTO> autoDTOs = userDTO.getAutos() != null ? userDTO.getAutos() : Collections.emptyList();
        List<Auto> convertedAutos = autoDTOs.stream().map(carMapper::toAuto)
                .collect(Collectors.toList());
        user.setAutos(convertedAutos);
        return user;
    }
}
