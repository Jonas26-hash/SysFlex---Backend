package pe.edu.upeu.sysflex.service.general.service;

import pe.edu.upeu.sysflex.dto.ChoferDTO;
import java.util.List;

public interface ChoferService {
    List<ChoferDTO> obtenerDisponibles();
    List<ChoferDTO> listarTodos();
}