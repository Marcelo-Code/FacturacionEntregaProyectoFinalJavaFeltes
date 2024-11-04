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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
    @Operation(summary = "Con este método se obtiene un usuario por su ID correspondiente: ", description = "Por defecto hay 10 usuarios pre-cargados, con sus IDs iniciando en 1 y terminando en 10.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuario no encontrado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> getUserById(@PathVariable Long id) {
        try {
            UsuarioDTO userDTO = userService.getUserById(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuario encontrado: ", userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Usuario no encontrado: ", e.getMessage()));
        }
    }

    // Obtener lista de todos los usuarios:
    // ------------------------------------

    @GetMapping(path = "/getallusuarios")
    @Operation(summary = "Con este método se obtiene una lista de todos los usuarios: ", description = "Por defecto hay 10 usuarios pre-cargados.")
    @ApiResponse(responseCode = "200", description = "Usuarios encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuarios no encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> getAllUsuarios() {
        try {
            List<UsuarioDTO> usuarios = userService.getAllUsers();
            return ResponseEntity.ok().body(new ApiResponseMsg("Usuarios encontrados: ", usuarios));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Usuarios no encontrados: ", e.getMessage()));
        }
    }

    // Obtener usuarios desde la API:
    // ------------------------------

    @GetMapping(path = "/api/getallusuarios")
    @Operation(summary = "Con este método se obtiene una lista de todos los usuarios de la API: ", description = "La API consta de una lista de 10 usuarios.")
    @ApiResponse(responseCode = "200", description = "Usuarios encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuarios no encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> getAllUsersApi() {

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
    @Operation(summary = "Con este método se obtiene un usuario por su ID correspondiente desde la API: ", description = "La API consta de 10 usuarios con sus IDs iniciando en 1 y terminando en 10.")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuario no encontrado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

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
    @Operation(summary = "Con este método se crea un usuario: ", description = "Deben asignarse las propiedades _**nombre**_, _**email**_ y _**telefono**_.\n\n"
            + //
            "<h2>¡¡IMPORTANTE!!</h2>\n\n"
            + //
            "La propiedad _**autos**_ será asignada automáticamente con un Array vacío, mientras que la propiedad _**id**_ será generada de manera automática.")
    @ApiResponse(responseCode = "200", description = "Usuario creado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuario no creado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

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
    @Operation(summary = "Con este método se asigna un usuario de la API a la lista de usuarios de la DB: ", description = "Debe ingresarse el ID del usuario de la API, consiguientemente el usuario será creado y se le asignará automáticamente el ID correspondiente de la lista de usuarios.")
    @ApiResponse(responseCode = "200", description = "Usuario creado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuario no creado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

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
    @Operation(summary = "Con este método se modifica un usuario: ", description = "Deben modificarse las propiedades _**nombre**_, _**email**_ y _**telefono**_.\n\n"
            + //
            "<h2>¡¡IMPORTANTE!!</h2>\n\n"
            + //
            "La propiedad _**autos**_ y la propiedad _**id**_ no serán modificadas.")
    @ApiResponse(responseCode = "200", description = "Usuario modificado", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuario no modificado", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

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
    @Operation(summary = "Con este método se elimina un usuario: ", description = "Mediante el ID se elimina el usuario correspondiente")
    @ApiResponse(responseCode = "200", description = "Usuario eliminado", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuario no eliminado", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

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
    @Operation(summary = "Con este método se elimina un usuario de la API: ", description = "Mediante el ID se elimina el usuario correspondiente, es a modo didáctico, ya que el usuario no será eliminado realmente")
    @ApiResponse(responseCode = "200", description = "Usuario eliminado", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuario no eliminado", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

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
