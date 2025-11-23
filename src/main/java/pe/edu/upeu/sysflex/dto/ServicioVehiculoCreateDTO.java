package pe.edu.upeu.sysflex.dto;

import lombok.Data;

@Data
public class ServicioVehiculoCreateDTO {
    private Long vehiculoId;
    private Long choferId;
    private Long ayudanteId;
    private Long almacenId;
    private Double volumenT;
}
