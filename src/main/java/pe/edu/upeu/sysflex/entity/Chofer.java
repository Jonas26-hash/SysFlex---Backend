package pe.edu.upeu.sysflex.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chofer")
@Getter @Setter
public class Chofer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private String numLicencia;
    private String dni;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
