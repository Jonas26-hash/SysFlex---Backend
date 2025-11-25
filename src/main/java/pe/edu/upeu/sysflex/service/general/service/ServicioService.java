package pe.edu.upeu.sysflex.service.general.service;

import pe.edu.upeu.sysflex.dto.ServicioCreateDTO;
import pe.edu.upeu.sysflex.dto.ServicioDetalleDTO;
import pe.edu.upeu.sysflex.dto.ServicioResumenDTO;

import java.time.LocalDate;
import java.util.List;

public interface ServicioService {
    List<ServicioResumenDTO> listar();
    ServicioDetalleDTO obtenerPorId(Long id);
    ServicioDetalleDTO crear(ServicioCreateDTO dto);
    ServicioDetalleDTO actualizar(Long id, ServicioCreateDTO dto);
    void eliminar(Long id);
    List<ServicioResumenDTO> buscar(String codigo, LocalDate fecha);
}
