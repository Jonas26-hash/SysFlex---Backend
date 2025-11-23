package pe.edu.upeu.sysflex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehiculo")
@Getter
@Setter
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private String modelo;
    private Double capacidad;
    private Double precio;
    private Integer altura;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
}
