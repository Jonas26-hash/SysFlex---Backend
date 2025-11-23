package pe.edu.upeu.sysflex.service.general.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysflex.dto.AlmacenDTO;
import pe.edu.upeu.sysflex.entity.Almacen;
import pe.edu.upeu.sysflex.mapper.AlmacenMapper;
import pe.edu.upeu.sysflex.repository.AlmacenRepository;
import pe.edu.upeu.sysflex.service.general.service.AlmacenService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlmacenServiceImpl implements AlmacenService {

    private final AlmacenRepository almacenRepository;
    private final AlmacenMapper almacenMapper;

    @Override
    public List<AlmacenDTO> listar() {
        log.info("Listando todos los almacenes");
        List<Almacen> almacenes = almacenRepository.findAll();
        log.info("Se encontraron {} almacenes", almacenes.size());

        return almacenes.stream()
                .map(almacenMapper::toDTO)
                .collect(Collectors.toList());
    }
}