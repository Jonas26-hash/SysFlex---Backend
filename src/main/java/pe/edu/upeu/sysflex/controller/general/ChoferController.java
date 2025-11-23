package pe.edu.upeu.sysflex.controller.general;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysflex.dto.ChoferDTO;
import pe.edu.upeu.sysflex.service.general.service.ChoferService;

import java.util.List;

@RestController
@RequestMapping("/api/choferes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChoferController {

    private final ChoferService choferService;

    @GetMapping("/disponibles")
    public ResponseEntity<List<ChoferDTO>> obtenerDisponibles() {
        List<ChoferDTO> choferes = choferService.obtenerDisponibles();
        return ResponseEntity.ok(choferes);
    }

    @GetMapping
    public ResponseEntity<List<ChoferDTO>> listarTodos() {
        List<ChoferDTO> choferes = choferService.listarTodos();
        return ResponseEntity.ok(choferes);
    }
}