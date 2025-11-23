package pe.edu.upeu.sysflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sysflex.entity.Servicio;

import java.time.LocalDate;
import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByCodigoContaining(String codigo);
    List<Servicio> findByFecha(LocalDate fecha);
    List<Servicio> findByCodigoContainingAndFecha(String codigo, LocalDate fecha);
}
