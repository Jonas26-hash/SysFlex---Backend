package pe.edu.upeu.sysflex.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysflex.dto.*;
import pe.edu.upeu.sysflex.entity.*;
import pe.edu.upeu.sysflex.repository.ServicioVehiculoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ServicioMapper {

    private final ServicioVehiculoRepository servicioVehiculoRepository;

    public ServicioResumenDTO toResumenDTO(Servicio entity) {
        if (entity == null) return null;
        ServicioResumenDTO dto = new ServicioResumenDTO();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setFecha(entity.getFecha());
        dto.setCliente(entity.getEmpresa().getNombre());
        dto.setEstado(entity.getEstado().getEstado());
        return dto;
    }

    public ServicioDetalleDTO toDetalleDTO(Servicio entity) {
        if (entity == null) return null;
        ServicioDetalleDTO dto = new ServicioDetalleDTO();
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setCliente(entity.getEmpresa().getNombre());
        dto.setFecha(entity.getFecha());
        dto.setEstado(entity.getEstado().getEstado());
        List<ServicioVehiculo> vehiculos = servicioVehiculoRepository.findByServicio(entity);
        List<ServicioVehiculoItemDTO> vehiculosDTO = vehiculos.stream()
                .map(this::toVehiculoItemDTO)
                .collect(Collectors.toList());
        dto.setVehiculos(vehiculosDTO);
        return dto;
    }

    public ServicioVehiculoItemDTO toVehiculoItemDTO(ServicioVehiculo entity) {
        if (entity == null) return null;
        ServicioVehiculoItemDTO dto = new ServicioVehiculoItemDTO();
        dto.setId(entity.getId());
        dto.setVehiculo(entity.getVehiculo().getModelo());
        dto.setPlaca(entity.getVehiculo().getPlaca());
        dto.setChofer(entity.getChofer().getNombreCompleto());
        dto.setAyudante(entity.getAyudante().getNombreCompleto());
        dto.setAlmacen(entity.getAlmacen().getNombre());
        dto.setVolumenT(entity.getVolumenT());
        return dto;
    }
}