package com.EntregaFinalJava.EntregaFinalJava.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.EntregaFinalJava.EntregaFinalJava.DTO.UsuarioDTO;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.AutoMapper;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.UsuarioApiMapper;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.UsuarioMapper;
import com.EntregaFinalJava.EntregaFinalJava.Model.Usuario;
import com.EntregaFinalJava.EntregaFinalJava.Model.UsuarioApi;
import com.EntregaFinalJava.EntregaFinalJava.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    UsuarioRepository userRepository;

    @Autowired
    UsuarioMapper userMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UsuarioApiMapper userApiMapper;

    @Autowired
    @Lazy
    AutoMapper carMapper;

    // Obtener todos los usuarios desde la API:
    // ----------------------------------------

    public List<UsuarioDTO> gatAllUsersApi() {
        UsuarioApi[] userApis = restTemplate.getForObject(BASE_URL, UsuarioApi[].class);
        return Arrays.stream(userApis)
                .map(userApiMapper::toUsuarioDTO)
                .collect(Collectors.toList());
    }

    // Obtener usuario por ID desde la API:
    // ------------------------------------

    public UsuarioApi getUserByIdApi(Long id) {
        String url = BASE_URL + "/" + id;
        try {
            UsuarioApi userApi = restTemplate.getForObject(url, UsuarioApi.class);
            return userApi;
        } catch (Exception e) {
            throw new EntityNotFoundException("Usuario con ID " + id + " no encontrado");
        }
    }

    // Obtener usuario por Id:
    // -----------------------

    public UsuarioDTO getUserById(Long id) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));
        UsuarioDTO userDTO = userMapper.toUsuarioDTO(user);
        return userDTO;
    }

    // Obtener usuario pot Id:
    // -----------------------

    public Usuario getUserByID(Long id) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));
        return user;
    }

    // Obtener lista de todos los usuarios:
    // ------------------------------------

    public List<UsuarioDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUsuarioDTO)
                .collect(Collectors.toList());
    }

    // Crear Usuario:
    // --------------

    public UsuarioDTO createUser(UsuarioDTO usuarioDTO) {
        Usuario usuario = userMapper.toUsuario(usuarioDTO);
        Usuario savedUsuario = userRepository.save(usuario);
        return userMapper.toUsuarioDTO(savedUsuario);
    }

    // Modificar usuario:
    // ------------------

    public UsuarioDTO updateUser(Long id, UsuarioDTO userDTO) {
        Usuario existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));
        Usuario toModifyUser = userMapper.toUsuario(userDTO);
        existingUser.setNombre(toModifyUser.getNombre());
        existingUser.setEmail(toModifyUser.getEmail());
        existingUser.setTelefono(toModifyUser.getTelefono());
        userRepository.save(existingUser);
        UsuarioDTO modifiedUser = userMapper.toUsuarioDTO(existingUser);
        return modifiedUser;
    }

    // Eliminar usuario:
    // -----------------

    public UsuarioDTO deleteUser(Long id) {
        Usuario userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));
        userRepository.deleteById(id);
        UsuarioDTO deletedUser = userMapper.toUsuarioDTO(userToDelete);
        return deletedUser;
    }

    // Eliminar usuario en la API:
    // ---------------------------

    public UsuarioApi deleteApiUser(Long id) {
        String url = BASE_URL + "/" + id;
        try {
            UsuarioApi userToDelete = restTemplate.getForObject(url, UsuarioApi.class);
            restTemplate.delete(url);
            return userToDelete;
        } catch (HttpClientErrorException.NotFound e) {
            throw new EntityNotFoundException("Usuario con ID " + id + " no encontrado");
        }
    }

    // Asignar un usuario de la API a la lista de usuarios:
    // ----------------------------------------------------

    public UsuarioDTO createUserApi(Long id) {
        String url = BASE_URL + "/" + id;
        try {
            UsuarioApi userToCreateApi = restTemplate.getForObject(url, UsuarioApi.class);
            UsuarioDTO userToCreateDTO = this.userApiMapper.toUsuarioDTO(userToCreateApi);
            UsuarioDTO userCreatedDTO = this.createUser(userToCreateDTO);
            return userCreatedDTO;
        } catch (HttpClientErrorException.NotFound e) {
            throw new EntityNotFoundException("Usuario con ID " + id + " no encontrado");
        }
    }

    // Crear usuario desde el main:
    // ----------------------------

    public void createUser(Usuario usuario) {
        this.userRepository.save(usuario);
    }
}
