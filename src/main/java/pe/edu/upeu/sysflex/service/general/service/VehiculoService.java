package pe.edu.upeu.sysflex.service.general.service;

import pe.edu.upeu.sysflex.dto.VehiculoDTO;
import java.util.List;

public interface VehiculoService {
    List<VehiculoDTO> obtenerDisponibles();
    List<VehiculoDTO> listarTodos();
}