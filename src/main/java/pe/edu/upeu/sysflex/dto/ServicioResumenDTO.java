package pe.edu.upeu.sysflex.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ServicioResumenDTO {
    private Long id;
    private String codigo;
    private LocalDate fecha;
    private String cliente;
    private String estado;
}
