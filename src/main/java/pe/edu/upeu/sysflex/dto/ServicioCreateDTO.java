package pe.edu.upeu.sysflex.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ServicioCreateDTO {
    private Long empresaId;
    private Long usuarioId;
    private LocalDate fecha;
    private List<ServicioVehiculoCreateDTO> vehiculos;
}
