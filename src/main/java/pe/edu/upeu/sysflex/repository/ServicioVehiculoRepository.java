package pe.edu.upeu.sysflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sysflex.entity.Servicio;
import pe.edu.upeu.sysflex.entity.ServicioVehiculo;

import java.util.List;

public interface ServicioVehiculoRepository extends JpaRepository<ServicioVehiculo, Long> {
    List<ServicioVehiculo> findByServicio(Servicio servicio);
}
