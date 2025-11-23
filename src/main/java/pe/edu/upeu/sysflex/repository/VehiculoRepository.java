package pe.edu.upeu.sysflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sysflex.entity.Vehiculo;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    List<Vehiculo> findByEstado_Estado(String estado);
}
