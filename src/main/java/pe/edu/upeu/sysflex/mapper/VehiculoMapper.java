package pe.edu.upeu.sysflex.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.sysflex.dto.VehiculoDTO;
import pe.edu.upeu.sysflex.entity.Vehiculo;

@Component
public class VehiculoMapper {

    public VehiculoDTO toDTO(Vehiculo entity) {
        if (entity == null) return null;

        VehiculoDTO dto = new VehiculoDTO();
        dto.setId(entity.getId());
        dto.setPlaca(entity.getPlaca());
        dto.setModelo(entity.getModelo());
        dto.setCapacidad(entity.getCapacidad());
        dto.setPrecio(entity.getPrecio());
        dto.setAltura(entity.getAltura());
        dto.setEstado(entity.getEstado().getEstado());
        return dto;
    }
}