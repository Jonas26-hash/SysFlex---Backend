package pe.edu.upeu.sysflex.service.general.service;

import pe.edu.upeu.sysflex.dto.EmpresaDTO;

public interface EmpresaService {
    EmpresaDTO buscarPorRuc(String ruc);
    EmpresaDTO obtenerPorId(Long id);
}