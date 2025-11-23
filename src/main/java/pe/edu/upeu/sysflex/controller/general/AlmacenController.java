package pe.edu.upeu.sysflex.controller.general;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysflex.dto.AlmacenDTO;
import pe.edu.upeu.sysflex.service.general.service.AlmacenService;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlmacenController {

    private final AlmacenService almacenService;

    @GetMapping
    public ResponseEntity<List<AlmacenDTO>> listar() {
        List<AlmacenDTO> almacenes = almacenService.listar();
        return ResponseEntity.ok(almacenes);
    }
}