package pe.edu.upeu.sysflex.dto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ServicioDetalleDTO {
    private Long id;
    private String codigo;
    private String cliente;
    private LocalDate fecha;
    private String estado;
    private List<ServicioVehiculoItemDTO> vehiculos;
}
