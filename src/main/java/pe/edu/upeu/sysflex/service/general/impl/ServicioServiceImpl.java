package pe.edu.upeu.sysflex.service.general.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.sysflex.controller.error.BusinessException;
import pe.edu.upeu.sysflex.controller.error.ResourceNotFoundException;
import pe.edu.upeu.sysflex.dto.*;
import pe.edu.upeu.sysflex.entity.*;
import pe.edu.upeu.sysflex.mapper.ServicioMapper;
import pe.edu.upeu.sysflex.repository.*;
import pe.edu.upeu.sysflex.service.general.service.ServicioService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;
    private final ServicioVehiculoRepository servicioVehiculoRepository;
    private final EmpresaRepository empresaRepository;
    private final VehiculoRepository vehiculoRepository;
    private final ChoferRepository choferRepository;
    private final AlmacenRepository almacenRepository;
    private final EstadoRepository estadoRepository;
    private final ServicioMapper servicioMapper;

    @Override
    public List<ServicioResumenDTO> listar() {
        log.info("Listando todos los servicios");
        List<Servicio> servicios = servicioRepository.findAll();
        log.info("Se encontraron {} servicios", servicios.size());

        return servicios.stream()
                .map(servicioMapper::toResumenDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServicioResumenDTO> buscar(String codigo, LocalDate fecha) {
        log.info("Buscando servicios - Código: {}, Fecha: {}", codigo, fecha);
        List<Servicio> servicios;

        if (codigo != null && fecha != null) {
            servicios = servicioRepository.findByCodigoContainingAndFecha(codigo, fecha);
        } else if (codigo != null) {
            servicios = servicioRepository.findByCodigoContaining(codigo);
        } else if (fecha != null) {
            servicios = servicioRepository.findByFecha(fecha);
        } else {
            servicios = servicioRepository.findAll();
        }

        log.info("Se encontraron {} servicios", servicios.size());
        return servicios.stream()
                .map(servicioMapper::toResumenDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServicioDetalleDTO obtenerPorId(Long id) {
        log.info("Obteniendo servicio con ID: {}", id);

        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "ID", id));

        log.info("Servicio encontrado: {}", servicio.getCodigo());
        return servicioMapper.toDetalleDTO(servicio);
    }

    @Override
    @Transactional
    public ServicioDetalleDTO crear(ServicioCreateDTO dto) {
        log.info("Iniciando creación de servicio");
        log.info("Empresa ID: {}", dto.getEmpresaId());
        log.info("Fecha: {}", dto.getFecha());
        log.info("Cantidad de vehículos: {}", dto.getVehiculos().size());

        try {
            log.debug("Paso 1: Buscando empresa con ID {}", dto.getEmpresaId());
            Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                    .orElseThrow(() -> {
                        log.error("Empresa no encontrada con ID: {}", dto.getEmpresaId());
                        return new ResourceNotFoundException("Empresa", "ID", dto.getEmpresaId());
                    });
            log.info("✓ Empresa encontrada: {}", empresa.getNombre());
            log.debug("Buscando estado 'Pendiente'");
            Estado estadoPendiente = estadoRepository.findByEstado("Pendiente")
                    .orElseThrow(() -> {
                        log.error("Estado 'Pendiente' no encontrado");
                        return new ResourceNotFoundException("Estado 'Pendiente' no encontrado");
                    });
            log.info("✓ Estado pendiente encontrado: ID {}", estadoPendiente.getId());
            String codigo = generarCodigoServicio();
            log.info("✓ Código generado: {}", codigo);
            log.debug("Creando servicio");
            Servicio servicio = new Servicio();
            servicio.setCodigo(codigo);
            servicio.setEmpresa(empresa);
            servicio.setEstado(estadoPendiente);
            servicio.setFecha(dto.getFecha());
            servicio = servicioRepository.save(servicio);
            log.info("✓ Servicio guardado con ID: {}", servicio.getId());
            log.debug("Buscando estado 'Ocupado'");
            Estado estadoOcupado = estadoRepository.findByEstado("Ocupado")
                    .orElseThrow(() -> {
                        log.error("Estado 'Ocupado' no encontrado");
                        return new ResourceNotFoundException("Estado 'Ocupado' no encontrado");
                    });
            log.info("✓ Estado ocupado encontrado: ID {}", estadoOcupado.getId());
            log.info("Procesando vehículos...");

            int vehiculoNum = 1;
            for (ServicioVehiculoCreateDTO vehiculoDTO : dto.getVehiculos()) {
                log.info("--- Vehículo {} de {} ---", vehiculoNum, dto.getVehiculos().size());
                log.debug("Vehículo ID: {}", vehiculoDTO.getVehiculoId());
                log.debug("Chofer ID: {}", vehiculoDTO.getChoferId());
                log.debug("Ayudante ID: {}", vehiculoDTO.getAyudanteId());
                log.debug("Almacén ID: {}", vehiculoDTO.getAlmacenId());
                Vehiculo vehiculo = vehiculoRepository.findById(vehiculoDTO.getVehiculoId())
                        .orElseThrow(() -> {
                            log.error("Vehículo no encontrado: ID {}", vehiculoDTO.getVehiculoId());
                            return new ResourceNotFoundException("Vehículo", "ID", vehiculoDTO.getVehiculoId());
                        });
                log.info("  ✓ Vehículo: {} - {}", vehiculo.getPlaca(), vehiculo.getModelo());
                Chofer chofer = choferRepository.findById(vehiculoDTO.getChoferId())
                        .orElseThrow(() -> {
                            log.error("Chofer no encontrado: ID {}", vehiculoDTO.getChoferId());
                            return new ResourceNotFoundException("Chofer", "ID", vehiculoDTO.getChoferId());
                        });
                log.info("  ✓ Chofer: {}", chofer.getNombreCompleto());

                Chofer ayudante = choferRepository.findById(vehiculoDTO.getAyudanteId())
                        .orElseThrow(() -> {
                            log.error("Ayudante no encontrado: ID {}", vehiculoDTO.getAyudanteId());
                            return new ResourceNotFoundException("Ayudante", "ID", vehiculoDTO.getAyudanteId());
                        });
                log.info("  ✓ Ayudante: {}", ayudante.getNombreCompleto());

                Almacen almacen = almacenRepository.findById(vehiculoDTO.getAlmacenId())
                        .orElseThrow(() -> {
                            log.error("Almacén no encontrado: ID {}", vehiculoDTO.getAlmacenId());
                            return new ResourceNotFoundException("Almacén", "ID", vehiculoDTO.getAlmacenId());
                        });
                log.info("  ✓ Almacén: {}", almacen.getNombre());

                ServicioVehiculo sv = new ServicioVehiculo();
                sv.setServicio(servicio);
                sv.setVehiculo(vehiculo);
                sv.setChofer(chofer);
                sv.setAyudante(ayudante);
                sv.setAlmacen(almacen);
                sv.setVolumenT(vehiculoDTO.getVolumenT());
                sv.setEstado(estadoPendiente);

                servicioVehiculoRepository.save(sv);
                log.info("  Relación servicio-vehículo guardada");

                vehiculo.setEstado(estadoOcupado);
                vehiculoRepository.save(vehiculo);
                log.debug("  Vehículo marcado como ocupado");

                chofer.setEstado(estadoOcupado);
                choferRepository.save(chofer);
                log.debug("  Chofer marcado como ocupado");

                ayudante.setEstado(estadoOcupado);
                choferRepository.save(ayudante);
                log.debug("  Ayudante marcado como ocupado");

                log.info("  Vehículo {} procesado correctamente", vehiculo.getPlaca());
                vehiculoNum++;
            }

            log.info("Servicio {} creado exitosamente", codigo);
            log.info("Total de vehículos asignados: {}", dto.getVehiculos().size());

            return obtenerPorId(servicio.getId());

        } catch (ResourceNotFoundException e) {
            log.error("Error: Recurso no encontrado - {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear servicio: {}", e.getMessage(), e);
            throw new BusinessException("Error al crear servicio: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public ServicioDetalleDTO actualizar(Long id, ServicioCreateDTO dto) {
        log.info("Actualizando servicio ID: {}", id);

        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "ID", id));

        log.warn("Actualización de servicios no implementada");
        throw new BusinessException("Actualización de servicios no implementada");
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        log.info("Iniciando eliminación de servicio ID: {}", id);

        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio", "ID", id));
        log.info("Servicio a eliminar: {}", servicio.getCodigo());
        List<ServicioVehiculo> vehiculos = servicioVehiculoRepository.findByServicio(servicio);
        log.info("Liberando {} recursos...", vehiculos.size());

        Estado estadoDisponible = estadoRepository.findByEstado("Disponible")
                .orElseThrow(() -> new ResourceNotFoundException("Estado 'Disponible' no encontrado"));

        int recursoNum = 1;
        for (ServicioVehiculo sv : vehiculos) {
            log.info("--- Recurso {} de {} ---", recursoNum, vehiculos.size());

            sv.getVehiculo().setEstado(estadoDisponible);
            vehiculoRepository.save(sv.getVehiculo());
            log.debug("  Vehículo {} liberado", sv.getVehiculo().getPlaca());

            sv.getChofer().setEstado(estadoDisponible);
            choferRepository.save(sv.getChofer());
            log.debug("  Chofer {} liberado", sv.getChofer().getNombreCompleto());

            sv.getAyudante().setEstado(estadoDisponible);
            choferRepository.save(sv.getAyudante());
            log.debug("  Ayudante {} liberado", sv.getAyudante().getNombreCompleto());

            recursoNum++;
        }

        servicioVehiculoRepository.deleteAll(vehiculos);
        log.info("✓ Relaciones servicio-vehículo eliminadas");

        servicioRepository.delete(servicio);
        log.info("✓ Servicio eliminado");

        log.info("Servicio {} eliminado correctamente", servicio.getCodigo());
    }

    private String generarCodigoServicio() {
        Long count = servicioRepository.count();
        String codigo = String.format("SE%02d", count + 1);
        log.debug("Código generado: {} (Total servicios: {})", codigo, count);
        return codigo;
    }
}