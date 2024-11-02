package com.EntregaFinalJava.EntregaFinalJava.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.EntregaFinalJava.EntregaFinalJava.DTO.CategoriaDTO;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.CategoriaMapper;
import com.EntregaFinalJava.EntregaFinalJava.Model.Categoria;
import com.EntregaFinalJava.EntregaFinalJava.Repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoryMapper;

    public Categoria getCategoria(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    // Obtener una lista de todos las categorías:
    // ------------------------------------------

    public List<CategoriaDTO> getAllCategories() {
        return categoriaRepository.findAll().stream()
                .map(categoryMapper::toCategoriaDTO)
                .collect(Collectors.toList());
    }

    // Crear una categoría:
    // --------------------
    public void altaCategoria(Categoria categoria) {
        this.categoriaRepository.save(categoria);
    }

    public CategoriaDTO createCategory(CategoriaDTO categoryDTO) {
        Categoria category = categoryMapper.toCategoria(categoryDTO);
        Categoria savedCategory = categoriaRepository.save(category);
        return categoryMapper.toCategoriaDTO(savedCategory);
    }

    // Eliminar una categoría:
    // -----------------------

    public CategoriaDTO deleteCategory(Long id) {
        Categoria categoryToDelete = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría con ID " + id + " no encontrada"));
        categoriaRepository.delete(categoryToDelete);
        CategoriaDTO deletedCategory = categoryMapper.toCategoriaDTO(categoryToDelete);
        return deletedCategory;
    }

    // Modificar una categoría:
    // ------------------------

    public CategoriaDTO updateCategory(Long id, CategoriaDTO categoryDTO) {
        Categoria existingCategory = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría con ID " + id + " no encontrada"));
        Categoria toModifyCategory = categoryMapper.toCategoria(categoryDTO);
        existingCategory.setNombre(toModifyCategory.getNombre());
        existingCategory.setDescripcion(toModifyCategory.getDescripcion());
        categoriaRepository.save(existingCategory);

        CategoriaDTO modifiedCategory = categoryMapper.toCategoriaDTO(toModifyCategory);

        return modifiedCategory;
    }

    public List<Categoria> mostrarCategorias() {
        return this.categoriaRepository.mostrarCategorias();
    }
}
