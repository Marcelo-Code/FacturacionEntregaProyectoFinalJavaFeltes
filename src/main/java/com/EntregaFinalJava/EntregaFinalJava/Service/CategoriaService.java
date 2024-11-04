package com.EntregaFinalJava.EntregaFinalJava.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.EntregaFinalJava.EntregaFinalJava.DTO.CategoriaDTO;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.CategoriaMapper;
import com.EntregaFinalJava.EntregaFinalJava.Model.Categoria;
import com.EntregaFinalJava.EntregaFinalJava.Repository.AutosRepository;
import com.EntregaFinalJava.EntregaFinalJava.Repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoryMapper;

    @Autowired
    private AutosRepository carRepository;

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

    // Obtener una categoría por id:
    // -----------------------------

    public CategoriaDTO getCategoryById(Long id) {
        Categoria category = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cateroría con ID " + id + " no encontrada"));
        CategoriaDTO categoryDTO = categoryMapper.toCategoriaDTO(category);
        return categoryDTO;
    }

    // Crear una categoría:
    // --------------------

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
        Categoria emptyCategory = new Categoria();
        categoriaRepository.save(emptyCategory);
        categoryToDelete.getAutos().forEach(auto -> auto.setCategoria(emptyCategory));
        carRepository.saveAll(categoryToDelete.getAutos());
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
        CategoriaDTO modifiedCategory = categoryMapper.toCategoriaDTO(existingCategory);
        return modifiedCategory;
    }

    // Obtener una lista de categorías desde el main:
    // ---------------------------------------------

    public List<Categoria> getCategories() {
        return this.categoriaRepository.getCategories();
    }

    // Crear una categoría desde el main:
    // ---------------------------------
    public void createCategory(Categoria categoria) {
        this.categoriaRepository.save(categoria);
    }
}
