package com.EntregaFinalJava.EntregaFinalJava.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.EntregaFinalJava.EntregaFinalJava.DTO.AutoDTO;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.AutoMapper;
import com.EntregaFinalJava.EntregaFinalJava.Model.Auto;
import com.EntregaFinalJava.EntregaFinalJava.Model.Categoria;
import com.EntregaFinalJava.EntregaFinalJava.Model.Usuario;
import com.EntregaFinalJava.EntregaFinalJava.Repository.AutosRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Service
public class AutoService {

    @Autowired
    private AutosRepository autosRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AutoMapper carMapper;

    @Autowired
    private UsuarioService userService;

    // Obtener auto por Id:
    // --------------------

    public AutoDTO getCarById(int id) {
        Auto car = autosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auto con ID " + id + " no encontrado"));
        AutoDTO carDto = carMapper.toAutoDTO(car);
        return carDto;
    }

    // Obtener una lista de todos los autos:
    // -------------------------------------

    public List<Auto> getAllCars() {
        return autosRepository.findAll();
    }

    // Obtener una lista de autos por marca:
    // -------------------------------------

    public List<AutoDTO> getCarsByBrand(String brand) {
        List<Auto> cars = autosRepository.getCarsByBrand(brand);
        List<AutoDTO> carDTOs = cars.stream()
                .map(carMapper::toAutoDTO)
                .collect(Collectors.toList());
        return carDTOs;
    }

    // Obtener lista de autos en el main:
    // ----------------------------------

    public List<Auto> getCarsMain() {
        return this.autosRepository.getCarsMain();
    }

    // Obtener lista de autos de año mayor que:
    // ----------------------------------------

    public List<AutoDTO> getCarsByYearGreaterThan(int year) {
        List<Auto> cars = autosRepository.getCarsByYearGreaterThan(year);
        List<AutoDTO> carDTOs = cars.stream()
                .map(carMapper::toAutoDTO)
                .collect(Collectors.toList());
        return carDTOs;
    }

    // Obtener lista de autos de año menor que:
    // ----------------------------------------

    public List<AutoDTO> getCarsByYearLessThan(int year) {
        List<Auto> cars = autosRepository.getCarsByYearLessThan(year);
        List<AutoDTO> carDTOs = cars.stream()
                .map(carMapper::toAutoDTO)
                .collect(Collectors.toList());
        return carDTOs;
    }

    public void altaAuto(Auto auto) {
        this.autosRepository.save(auto);
    }

    // Eliminar un un auto:
    // --------------------

    public AutoDTO deleteCar(int id) {
        Auto carToDelete = autosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auto con ID " + id + " no encontrado"));
        autosRepository.delete(carToDelete);
        AutoDTO deletedCar = carMapper.toAutoDTO(carToDelete);
        return (deletedCar);
    }

    // Crear un auto:
    // --------------

    public AutoDTO createCar(AutoDTO autoDTO) {
        Auto car = carMapper.toAuto(autoDTO);
        Auto savedCar = autosRepository.save(car);
        return carMapper.toAutoDTO(savedCar);
    }

    // Modificar un auto:
    // ------------------

    public AutoDTO upDateCar(int id, AutoDTO carDTO) {
        Auto existingCar = autosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auto con ID " + id + " no encontrado"));
        Auto toModifyCar = carMapper.toAuto(carDTO);
        existingCar.setMarca(toModifyCar.getMarca());
        existingCar.setModelo(toModifyCar.getModelo());
        existingCar.setAnio(toModifyCar.getAnio());
        existingCar.setPrecio(toModifyCar.getPrecio());

        Long newCategoryId = toModifyCar.getCategoriaId();
        Categoria newCategory = categoriaService.getCategoria(newCategoryId);
        existingCar.setCategoria(newCategory);

        Long newUserId = toModifyCar.getUsuarioId();
        Usuario newUser = userService.getUserByID(newUserId);
        existingCar.setUsuario(newUser);
        autosRepository.save(existingCar);

        AutoDTO modifiedCar = carMapper.toAutoDTO(existingCar);

        return modifiedCar;
    }

}
