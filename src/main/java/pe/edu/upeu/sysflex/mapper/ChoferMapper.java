package pe.edu.upeu.sysflex.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.sysflex.dto.ChoferDTO;
import pe.edu.upeu.sysflex.entity.Chofer;

@Component
public class ChoferMapper {

    public ChoferDTO toDTO(Chofer entity) {
        if (entity == null) return null;

        ChoferDTO dto = new ChoferDTO();
        dto.setId(entity.getId());
        dto.setNombreCompleto(entity.getNombreCompleto());
        dto.setNumLicencia(entity.getNumLicencia());
        dto.setDni(entity.getDni());
        return dto;
    }
}