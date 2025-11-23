package pe.edu.upeu.sysflex.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.sysflex.dto.AlmacenDTO;
import pe.edu.upeu.sysflex.entity.Almacen;

@Component
public class AlmacenMapper {

    public AlmacenDTO toDTO(Almacen entity) {
        if (entity == null) return null;

        AlmacenDTO dto = new AlmacenDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public Almacen toEntity(AlmacenDTO dto) {
        if (dto == null) return null;

        Almacen entity = new Almacen();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        return entity;
    }
}