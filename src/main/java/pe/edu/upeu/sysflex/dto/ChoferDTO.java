package pe.edu.upeu.sysflex.dto;

import lombok.Data;

@Data
public class ChoferDTO {
    private Long id;
    private String nombreCompleto;
    private String numLicencia;
    private String dni;
}