package pe.edu.upeu.sysflex.controller.general;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysflex.dto.ServicioCreateDTO;
import pe.edu.upeu.sysflex.dto.ServicioDetalleDTO;
import pe.edu.upeu.sysflex.dto.ServicioResumenDTO;
import pe.edu.upeu.sysflex.service.general.service.ServicioService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServicioController {

    private final ServicioService servicioService;

    @GetMapping
    public List<ServicioResumenDTO> listar(
            @RequestParam(required = false) String codigo,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha
    ) {
        if (codigo != null || fecha != null) {
            return servicioService.buscar(codigo, fecha);
        }
        return servicioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDetalleDTO> obtenerPorId(@PathVariable Long id) {
        ServicioDetalleDTO dto = servicioService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ServicioDetalleDTO> crear(@RequestBody ServicioCreateDTO dto) {
        ServicioDetalleDTO creado = servicioService.crear(dto);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDetalleDTO> actualizar(
            @PathVariable Long id,
            @RequestBody ServicioCreateDTO dto
    ) {
        ServicioDetalleDTO actualizado = servicioService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
