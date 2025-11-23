package pe.edu.upeu.sysflex.dto;

import lombok.Data;

@Data
public class EmpresaDTO {
    private Long id;
    private String nombre;
    private Long ruc;
    private String telefono;
}