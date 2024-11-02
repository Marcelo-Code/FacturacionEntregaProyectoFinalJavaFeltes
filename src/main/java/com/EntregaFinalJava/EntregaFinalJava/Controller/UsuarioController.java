package com.EntregaFinalJava.EntregaFinalJava.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.EntregaFinalJava.EntregaFinalJava.DTO.UsuarioDTO;
import com.EntregaFinalJava.EntregaFinalJava.Model.UsuarioApi;
import com.EntregaFinalJava.EntregaFinalJava.Service.UsuarioService;
import com.EntregaFinalJava.EntregaFinalJava.Utils.ApiResponseMsg;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    // ------------------------
    // GET
    // ------------------------
    // Obtener usuario por Id:
    // -----------------------

    @GetMapping("/getusuariobyid/{id}")

    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UsuarioDTO userDTO = userService.getUserById(id);
            return ResponseEntity.ok(userDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Usuario no encontrado", e.getMessage()));
        }
    }

    // Obtener lista de todos los usuarios:
    // ------------------------------------

    @GetMapping(path = "/getallusuarios")

    public ResponseEntity<?> getAllUsuarios() {
        try {
            List<UsuarioDTO> usuarios = userService.getAllUsers();
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuarios encontrados: ", usuarios));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Usuarios no encontrados", e.getMessage()));
        }
    }

    // Obtener usuarios desde la API:
    // ------------------------------

    @GetMapping(path = "/api/getallusuarios")

    public ResponseEntity<?> getAllUsersApi() {

        try {
            List<UsuarioDTO> usuarios = userService.gatAllUsersApi();
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuarios encontrados: ", usuarios));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar usuarios: ", e.getMessage()));
        }
    }

    // Obtener usuario por ID desde la API:
    // ------------------------------------

    @GetMapping(path = "/api/getusuariobyid/{id}")

    public ResponseEntity<ApiResponseMsg> getUserByIdApi(@PathVariable Long id) {
        try {
            UsuarioApi userAPI = userService.getUserByIdApi(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuario encontrado: ", userAPI));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar usuario: ", e.getMessage()));
        }
    }

    // --------------
    // POST
    // --------------
    // Crear Usuario:
    // --------------

    @PostMapping(path = "/createusuario")

    public ResponseEntity<ApiResponseMsg> createUser(@RequestBody UsuarioDTO userDTO) {
        try {
            UsuarioDTO createdUsuarioDTO = userService.createUser(userDTO);
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuario creado: ", createdUsuarioDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al crear usuario: ", e.getMessage()));
        }
    }

    // Crear un usuario desde un usuario de la API:
    // --------------------------------------------

    @PostMapping(path = "/api/createusuario/{id}")

    public ResponseEntity<ApiResponseMsg> createUserApi(@PathVariable Long id) {
        try {
            UsuarioDTO userDTO = userService.createUserApi(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuario creado desde API: ", userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseMsg("Error al tratar de crear el usuario desde API: ", e.getMessage()));
        }
    }

    // -------------------
    // PUT
    // -------------------
    // Modificar Usuario:
    // ------------------

    @PutMapping(path = "/modifyusuario/{id}")

    public ResponseEntity<ApiResponseMsg> updateUser(@PathVariable Long id, @RequestBody UsuarioDTO userDTO) {
        try {
            UsuarioDTO modifiedUser = userService.updateUser(id, userDTO);
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuario modificado: ", modifiedUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseMsg("Error al modificar el usuario: ", e.getMessage()));
        }
    }

    // ------------------
    // DELETE
    // ------------------
    // Eliminar usuario:
    // -----------------

    @DeleteMapping(path = "/deleteusuario/{id}")

    public ResponseEntity<ApiResponseMsg> deteleUser(@PathVariable Long id) {
        try {
            UsuarioDTO deletedUser = userService.deleteUser(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuario eliminado: ", deletedUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseMsg("Error al tratar de eliminar el usuario: ", e.getMessage()));
        }
    }

    // Eliminar usuario en la API:
    // ---------------------------

    @DeleteMapping(path = "/api/deleteusuario/{id}")

    public ResponseEntity<ApiResponseMsg> deleteApiUser(@PathVariable Long id) {
        try {
            UsuarioApi deletedUser = userService.deleteApiUser(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuario eliminado: ", deletedUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseMsg("Error al tratar de eliminar el usuario: ", e.getMessage()));
        }
    }
}
