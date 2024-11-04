package com.EntregaFinalJava.EntregaFinalJava.Controller;

import java.util.List;
import java.util.stream.Collectors;
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
import com.EntregaFinalJava.EntregaFinalJava.DTO.AutoDTO;
import com.EntregaFinalJava.EntregaFinalJava.DTO.UsuarioDTO;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.AutoMapper;
import com.EntregaFinalJava.EntregaFinalJava.Model.Auto;
import com.EntregaFinalJava.EntregaFinalJava.Service.AutoService;
import com.EntregaFinalJava.EntregaFinalJava.Service.CategoriaService;
import com.EntregaFinalJava.EntregaFinalJava.Utils.ApiResponseMsg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/autos")
public class AutoController {

    @Autowired
    AutoService autoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    AutoMapper autoMapper;

    // -------------------------------------
    // GET
    // -------------------------------------
    // Obtener una lista de todos los autos:
    // -------------------------------------

    @GetMapping("/getallautos")
    @Operation(summary = "Con este método se obtiene una lista de todos los autos: ", description = "Por defecto hay 30 autos pre-cargados.")
    @ApiResponse(responseCode = "200", description = "Autos encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Autos no encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> getAllCars() {
        try {
            List<Auto> autos = autoService.getAllCars();
            List<AutoDTO> autosDTO = autos.stream()
                    .map(autoMapper::toAutoDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(new ApiResponseMsg("Autos encontrados: ", autosDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar autos: ", e.getMessage()));
        }
    }

    // Obtener una lista de autos por marca:
    // -------------------------------------

    @GetMapping("/getautosbybrand/{brand}")
    @Operation(summary = "Con este método se obtiene una lista de todos los autos por marca: ", description = "Debe ingresarse la propiedad _**marca**_.")
    @ApiResponse(responseCode = "200", description = "Autos encontrados", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Autos no encontrados", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> getCarsByBrand(@PathVariable String brand) {
        try {
            List<AutoDTO> carDTOs = autoService.getCarsByBrand(brand);
            return ResponseEntity.ok().body(new ApiResponseMsg("Autos encontrados: ", carDTOs));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar autos: ", e.getMessage()));
        }
    }

    // Obtener auto por Id:
    // --------------------

    @GetMapping("/getautobyid/{id}")
    @Operation(summary = "Con este método se obtiene un auto por su ID correspondiente: ", description = "Por defecto hay 30 autos pre-cargados, con sus IDs iniciando en 1 y terminando en 30.")
    @ApiResponse(responseCode = "200", description = "Auto encontrado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Auto no encontrado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> getCarById(@PathVariable int id) {
        try {
            AutoDTO carDTO = autoService.getCarById(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Auto encontrado: ", carDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar auto: ", e.getMessage()));
        }
    }

    // Obtener lista de autos de año mayor que:
    // ----------------------------------------

    @GetMapping("/getautobyyeargreaterthan/{year}")
    @Operation(summary = "Con este método se obtiene una lista de todos los autos con años mayores al especificado en el parámetro ingresado: ", description = "Por defecto hay 30 autos pre-cargados cuyos años van desde 2019 al 2023.")
    @ApiResponse(responseCode = "200", description = "Autos encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Autos no encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> getCarsByYearGreaterThan(@PathVariable int year) {
        try {
            List<AutoDTO> carDTOs = autoService.getCarsByYearGreaterThan(year);
            return ResponseEntity.ok().body(new ApiResponseMsg("Autos encontrados: ", carDTOs));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar autos: ", e.getMessage()));
        }
    }

    // Obtener lista de autos de año menor que:
    // ----------------------------------------

    @GetMapping("/getautobyyearlessthan/{year}")
    @Operation(summary = "Con este método se obtiene una lista de todos los autos con años menores al especificado en el parámetro ingresado: ", description = "Por defecto hay 30 autos pre-cargados cuyos años van desde 2019 al 2023.")
    @ApiResponse(responseCode = "200", description = "Autos encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Autos no encontrados.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> getCarsByYearLessThan(@PathVariable int year) {
        try {
            List<AutoDTO> carDTOs = autoService.getCarsByYearLessThan(year);
            return ResponseEntity.ok().body(new ApiResponseMsg("Autos encontrados: ", carDTOs));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar autos: ", e.getMessage()));
        }
    }

    // --------------
    // POST
    // --------------
    // Crear un auto:
    // --------------

    @PostMapping("/createauto")
    @Operation(summary = "Con este método se crea un auto: ", description = "Deben asignarse las propiedades _**marca**_, _**modelo**_, _**anio**_, _**precio**_, _**categoriaId**_ y _**usuarioId**_.\n\n"
            + //
            "<h2>¡¡IMPORTANTE!!</h2>\n\n"
            + //
            "La propiedad _**id**_ será generada automáticamente mientras que las propiedades _**categoriaNombre**_ y _**usuarioNombre**_ serán asignadas automáticamente de acuerdo a las propiedades _**categoriaId**_ y _**usuarioId**_ ingresadas respectivamente.")
    @ApiResponse(responseCode = "200", description = "Auto creado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Auto no creado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> createCar(@RequestBody AutoDTO autoDTO) {
        try {
            AutoDTO createdCarDTO = autoService.createCar(autoDTO);
            return ResponseEntity.ok().body(new ApiResponseMsg("Auto creado: ", createdCarDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseMsg("Error al tratar de crear auto: ", e.getMessage()));
        }
    }

    // ------------------
    // PUT
    // ------------------
    // Modificar un auto:
    // ------------------

    @PutMapping("/modifyauto/{id}")
    @Operation(summary = "Con este método se modifica un auto: ", description = "Deben modificarse las propiedades _**marca**_, _**modelo**_, _**anio**_, _**precio**_, _**categoriaId**_ y _**usuarioId**_.\n\n"
            + //
            "<h2>¡¡IMPORTANTE!!</h2>\n\n"
            + //
            "La propiedad _**id**_ no se modifica, mientras que las propiedades _**categoriaNombre**_ y _**usuarioNombre**_ serán asignadas automáticamente de acuerdo a las propiedades _**categoriaId**_ y _**usuarioId**_ ingresadas respectivamente.")
    @ApiResponse(responseCode = "200", description = "Auto modificado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Auto no modificado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> upDateCar(@PathVariable int id, @RequestBody AutoDTO carDto) {
        try {
            AutoDTO modifiedCar = autoService.upDateCar(id, carDto);
            return ResponseEntity.ok().body(new ApiResponseMsg("Auto modificado: ", modifiedCar));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseMsg("Error al tratar modificar auto: ", e.getMessage()));
        }
    }

    // -----------------
    // DELETE
    // -----------------
    // Eliminar un auto:
    // -----------------

    @DeleteMapping("/deleteauto/{id}")
    @Operation(summary = "Con este método se elimina un auto: ", description = "Mediante el ID se elimina el auto correspondiente.")
    @ApiResponse(responseCode = "200", description = "Auto eliminado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
    @ApiResponse(responseCode = "400", description = "Auto no eliminado.", content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))

    public ResponseEntity<ApiResponseMsg> deleteCar(@PathVariable int id) {
        try {
            AutoDTO detetedCar = autoService.deleteCar(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Auto eliminado: ", detetedCar));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseMsg("Error al tratar de eliminar auto: ", e.getMessage()));
        }
    }
}
