package pe.edu.upeu.sysflex.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.sysflex.dto.EmpresaDTO;
import pe.edu.upeu.sysflex.entity.Empresa;

@Component
public class EmpresaMapper {

    public EmpresaDTO toDTO(Empresa entity) {
        if (entity == null) return null;

        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setRuc(entity.getRuc());
        dto.setTelefono(entity.getTelefono());
        return dto;
    }
}