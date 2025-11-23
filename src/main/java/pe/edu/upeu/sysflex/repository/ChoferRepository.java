package pe.edu.upeu.sysflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sysflex.entity.Chofer;

import java.util.List;

public interface ChoferRepository extends JpaRepository<Chofer, Long> {
    List<Chofer> findByEstado_Estado(String estado);
}