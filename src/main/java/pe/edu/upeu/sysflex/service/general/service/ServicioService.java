package pe.edu.upeu.sysflex.service.general.service;

import pe.edu.upeu.sysflex.dto.ServicioCreateDTO;
import pe.edu.upeu.sysflex.dto.ServicioDetalleDTO;
import pe.edu.upeu.sysflex.dto.ServicioResumenDTO;

import java.time.LocalDate;
import java.util.List;

public interface ServicioService {

    // Lista para la primera pantalla (tabla de servicios)
    List<ServicioResumenDTO> listar();

    // Detalle de un servicio (para ver vehículos seleccionados, etc.)
    ServicioDetalleDTO obtenerPorId(Long id);

    // Registrar un nuevo servicio (con sus vehículos)
    ServicioDetalleDTO crear(ServicioCreateDTO dto);

    // Editar un servicio existente (si luego lo necesitas)
    ServicioDetalleDTO actualizar(Long id, ServicioCreateDTO dto);

    // Eliminar servicio
    void eliminar(Long id);

    // Buscar por código y/o fecha (para el input "Buscar por código o fecha...")
    List<ServicioResumenDTO> buscar(String codigo, LocalDate fecha);
}
