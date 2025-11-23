package pe.edu.upeu.sysflex.service.general.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysflex.dto.VehiculoDTO;
import pe.edu.upeu.sysflex.entity.Vehiculo;
import pe.edu.upeu.sysflex.mapper.VehiculoMapper;
import pe.edu.upeu.sysflex.repository.VehiculoRepository;
import pe.edu.upeu.sysflex.service.general.service.VehiculoService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final VehiculoMapper vehiculoMapper;

    @Override
    public List<VehiculoDTO> obtenerDisponibles() {
        log.info("Obteniendo vehículos disponibles");
        List<Vehiculo> vehiculos = vehiculoRepository.findByEstado_Estado("Disponible");
        log.info("Se encontraron {} vehículos disponibles", vehiculos.size());

        return vehiculos.stream()
                .map(vehiculoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehiculoDTO> listarTodos() {
        log.info("Listando todos los vehículos");
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        log.info("Se encontraron {} vehículos", vehiculos.size());

        return vehiculos.stream()
                .map(vehiculoMapper::toDTO)
                .collect(Collectors.toList());
    }
}