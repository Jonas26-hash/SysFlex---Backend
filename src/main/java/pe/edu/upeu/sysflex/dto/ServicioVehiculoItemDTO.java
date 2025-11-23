package pe.edu.upeu.sysflex.dto;

import lombok.Data;

@Data
public class ServicioVehiculoItemDTO {
    private Long id;
    private String vehiculo;
    private String placa;
    private String chofer;
    private String ayudante;
    private String almacen;
    private Double volumenT;
}
