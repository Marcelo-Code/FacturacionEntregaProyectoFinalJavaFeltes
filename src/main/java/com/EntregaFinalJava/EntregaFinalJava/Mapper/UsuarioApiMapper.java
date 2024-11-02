package com.EntregaFinalJava.EntregaFinalJava.Mapper;

import org.springframework.stereotype.Component;
import com.EntregaFinalJava.EntregaFinalJava.DTO.UsuarioDTO;
import com.EntregaFinalJava.EntregaFinalJava.Model.UsuarioApi;
import java.util.ArrayList;

@Component
public class UsuarioApiMapper {
    public UsuarioDTO toUsuarioDTO(UsuarioApi usuarioApi) {
        if (usuarioApi == null) {
            return null;
        }
        return UsuarioDTO.builder()
                .id(usuarioApi.getId())
                .nombre(usuarioApi.getName())
                .email(usuarioApi.getEmail())
                .telefono(usuarioApi.getPhone())
                .autos(new ArrayList<>())
                .build();
    }
}
