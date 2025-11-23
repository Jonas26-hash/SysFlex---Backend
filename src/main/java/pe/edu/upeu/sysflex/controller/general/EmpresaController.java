package pe.edu.upeu.sysflex.controller.general;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.sysflex.dto.EmpresaDTO;
import pe.edu.upeu.sysflex.service.general.service.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping("/buscar")
    public ResponseEntity<EmpresaDTO> buscarPorRuc(@RequestParam String ruc) {
        EmpresaDTO empresa = empresaService.buscarPorRuc(ruc);
        return ResponseEntity.ok(empresa);
    }

    @GetMapping
    public ResponseEntity<EmpresaDTO> obtenerEmpresaPrincipal() {
        // Como solo trabajas con Sofía, retorna directamente
        EmpresaDTO empresa = empresaService.obtenerPorId(1L);
        return ResponseEntity.ok(empresa);
    }
}