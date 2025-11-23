package pe.edu.upeu.sysflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sysflex.entity.Estado;
import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByEstado(String estado);
}