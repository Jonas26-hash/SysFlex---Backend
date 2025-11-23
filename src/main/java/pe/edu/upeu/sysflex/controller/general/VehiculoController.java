package pe.edu.upeu.sysflex.controller.general;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysflex.dto.VehiculoDTO;
import pe.edu.upeu.sysflex.service.general.service.VehiculoService;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @GetMapping("/disponibles")
    public ResponseEntity<List<VehiculoDTO>> obtenerDisponibles() {
        List<VehiculoDTO> vehiculos = vehiculoService.obtenerDisponibles();
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> listarTodos() {
        List<VehiculoDTO> vehiculos = vehiculoService.listarTodos();
        return ResponseEntity.ok(vehiculos);
    }
}