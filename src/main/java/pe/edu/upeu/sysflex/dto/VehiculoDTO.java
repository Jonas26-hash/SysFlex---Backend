package pe.edu.upeu.sysflex.dto;

import lombok.Data;

@Data
public class VehiculoDTO {
    private Long id;
    private String placa;
    private String modelo;
    private Double capacidad;
    private Double precio;
    private Integer altura;
    private String estado;
}