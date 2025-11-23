package pe.edu.upeu.sysflex.service.general.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysflex.controller.error.ResourceNotFoundException;
import pe.edu.upeu.sysflex.dto.EmpresaDTO;
import pe.edu.upeu.sysflex.entity.Empresa;
import pe.edu.upeu.sysflex.mapper.EmpresaMapper;
import pe.edu.upeu.sysflex.repository.EmpresaRepository;
import pe.edu.upeu.sysflex.service.exception.ServiceException;
import pe.edu.upeu.sysflex.service.general.service.EmpresaService;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    @Override
    public EmpresaDTO buscarPorRuc(String ruc) {
        try {
            log.info("Buscando empresa con RUC: {}", ruc);
            Long rucLong = Long.parseLong(ruc);

            Empresa empresa = empresaRepository.findByRuc(rucLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Empresa", "RUC", ruc));

            log.info("Empresa encontrada: {}", empresa.getNombre());
            return empresaMapper.toDTO(empresa);

        } catch (NumberFormatException e) {
            log.error("RUC inválido: {}", ruc);
            throw new ServiceException("RUC inválido: " + ruc);
        }
    }

    @Override
    public EmpresaDTO obtenerPorId(Long id) {
        log.info("Buscando empresa con ID: {}", id);

        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa", "ID", id));

        log.info("Empresa encontrada: {}", empresa.getNombre());
        return empresaMapper.toDTO(empresa);
    }
}