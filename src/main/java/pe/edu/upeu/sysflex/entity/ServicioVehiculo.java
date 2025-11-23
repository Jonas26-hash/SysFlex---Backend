package pe.edu.upeu.sysflex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "servicio_vehiculo")
@Getter @Setter
public class ServicioVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "chofer_id")
    private Chofer chofer;

    @ManyToOne
    @JoinColumn(name = "ayudante_id")
    private Chofer ayudante;

    @ManyToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacen;

    private Double volumenT;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
}
