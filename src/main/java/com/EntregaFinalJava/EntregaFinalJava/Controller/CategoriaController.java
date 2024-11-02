package com.EntregaFinalJava.EntregaFinalJava.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.EntregaFinalJava.EntregaFinalJava.DTO.CategoriaDTO;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.CategoriaMapper;
import com.EntregaFinalJava.EntregaFinalJava.Service.CategoriaService;
import com.EntregaFinalJava.EntregaFinalJava.Utils.ApiResponseMsg;

@Controller
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

    public ResponseEntity<ApiResponseMsg> getAllCategories() {
        try {
            List<CategoriaDTO> categoryDTOs = categoriaService.getAllCategories();
            return ResponseEntity.ok().body(new ApiResponseMsg("Categorías encontradas: ", categoryDTOs));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar categorías: ", e.getMessage()));
        }
    }

    @GetMapping("/getcategoriabyid/{id}")

    public ResponseEntity<ApiResponseMsg> getCategoryById(@PathVariable Long id) {
        try {
            CategoriaDTO categoryDTO = categoriaService.getCategoryById((id));
            return ResponseEntity.ok().body(new ApiResponseMsg("Categoría encontrada", categoryDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al buscar categoría", e.getMessage()));
        }
    }

    // --------------------
    // POST
    // --------------------
    // Crear una categoría:
    // --------------------

    @PostMapping("/createcategoria")

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
