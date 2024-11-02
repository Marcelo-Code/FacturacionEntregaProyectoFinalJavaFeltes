package com.EntregaFinalJava.EntregaFinalJava.Controller;

import java.util.List;
import java.util.stream.Collectors;
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
import com.EntregaFinalJava.EntregaFinalJava.DTO.AutoDTO;
import com.EntregaFinalJava.EntregaFinalJava.Mapper.AutoMapper;
import com.EntregaFinalJava.EntregaFinalJava.Model.Auto;
import com.EntregaFinalJava.EntregaFinalJava.Service.AutoService;
import com.EntregaFinalJava.EntregaFinalJava.Service.CategoriaService;
import com.EntregaFinalJava.EntregaFinalJava.Utils.ApiResponseMsg;

@Controller
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
