package pe.edu.upeu.sysflex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.sysflex.entity.Empresa;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByRuc(Long ruc);
}
