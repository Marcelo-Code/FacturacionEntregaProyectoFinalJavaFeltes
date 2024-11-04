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
import com.EntregaFinalJava.EntregaFinalJava.DTO.CategoriaDTO;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.CategoriaMapper;
import com.EntregaFinalJava.EntregaFinalJava.Service.CategoriaService;
import com.EntregaFinalJava.EntregaFinalJava.Utils.ApiResponseMsg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaMapper categoriaMapper;

    // ----------------------------------------
    // GET
    // ------------------------------------------
    // Obtener una lista de todos las categorías:
    // ------------------------------------------

    @GetMapping("/getallcategorias")
    @Operation(summary = "Con este método se obtiene una lista de todas las categorías: ", description = "Por defecto hay 10 categorías pre-cargadas.")
    @ApiResponse(responseCode = "200", description = "Categprías encontradas.", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))
    @ApiResponse(responseCode = "400", description = "Categorías no encontradas.", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))

    public ResponseEntity<ApiResponseMsg> getAllCategories() {
        try {
            List<CategoriaDTO> categoryDTOs = categoriaService.getAllCategories();
            return ResponseEntity.ok().body(new ApiResponseMsg("Categorías encontradas: ", categoryDTOs));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar categorías: ", e.getMessage()));
        }
    }

    @GetMapping("/getcategoriabyid/{id}")
    @Operation(summary = "Con este método se obtiene una categoría por su ID correspondiente: ", description = "Por defecto hay 10 categorías pre-cargadas, con sus IDs iniciando en 1 y terminando en 10.")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada.", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))
    @ApiResponse(responseCode = "400", description = "Categoría no encontrada.", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))

    public ResponseEntity<ApiResponseMsg> getCategoryById(@PathVariable Long id) {
        try {
            CategoriaDTO categoryDTO = categoriaService.getCategoryById((id));
            return ResponseEntity.ok().body(new ApiResponseMsg("Categoría encontrada: ", categoryDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar categoría: ", e.getMessage()));
        }
    }

    // --------------------
    // POST
    // --------------------
    // Crear una categoría:
    // --------------------

    @PostMapping("/createcategoria")
    @Operation(summary = "Con este método se crea una categoría: ", description = "Deben asignarse las propiedades _**nombre**_, _**descripcion**_.\n\n"
            + //
            "<h2>¡¡IMPORTANTE!!</h2>\n\n"
            + //
            "La propiedad _**autos**_ será asignada de manera automática con un array vacío, mientras que la propiedad _**id**_ será generada automáticamente.")
    @ApiResponse(responseCode = "200", description = "Categoría creada.", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))
    @ApiResponse(responseCode = "400", description = "Categoría no creada.", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))

    public ResponseEntity<ApiResponseMsg> createCategory(@RequestBody CategoriaDTO categoryDTO) {
        try {
            CategoriaDTO createdCategoryDTO = categoriaService.createCategory(categoryDTO);
            return ResponseEntity.ok().body(new ApiResponseMsg("Categoría creada: ", createdCategoryDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al crear categoría: ", e.getMessage()));
        }
    }

    // -----------------------
    // DELETE
    // -----------------------
    // Eliminar una categoría:
    // -----------------------

    @DeleteMapping("/deletecategoria/{id}")
    @Operation(summary = "Con este método se elimina una categoría: ", description = "Mediante el ID se elimina la categoría correspondiente.")
    @ApiResponse(responseCode = "200", description = "Categoría eliminada.", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))
    @ApiResponse(responseCode = "400", description = "Categoría no eliminada.", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))

    public ResponseEntity<ApiResponseMsg> deleteCategory(@PathVariable Long id) {
        try {
            CategoriaDTO deletedCategory = categoriaService.deleteCategory(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Categoría eliminada: ", deletedCategory));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseMsg("Error al tratar de eliminar la categoría: ", e.getMessage()));
        }
    }

    // ------------------------
    // PUT
    // ------------------------
    // Modificar una categoría:
    // ------------------------

    @PutMapping("/modifycategoria/{id}")
    @Operation(summary = "Con este método se modifica una categoría: ", description = "Deben modificarse las propiedades _**nombre**_ y _**descripcion**.\n\n"
            + //
            "<h2>¡¡IMPORTANTE!!</h2>\n\n"
            + //
            "La propiedad _**autos**_ y la propiedad _**id**_ no serán modificadas.")
    @ApiResponse(responseCode = "200", description = "Usuario modificado", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))
    @ApiResponse(responseCode = "400", description = "Usuario no modificado", content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))

    public ResponseEntity<ApiResponseMsg> updateCategory(@PathVariable Long id, @RequestBody CategoriaDTO categoryDTO) {
        try {
            CategoriaDTO modifiedCategory = categoriaService.updateCategory(id, categoryDTO);
            return ResponseEntity.ok().body(new ApiResponseMsg("Categoría modificada: ", modifiedCategory));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseMsg("Error al modificar categoría: ", e.getMessage()));
        }
    }
}
