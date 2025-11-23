package pe.edu.upeu.sysflex.service.general.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysflex.dto.ChoferDTO;
import pe.edu.upeu.sysflex.entity.Chofer;
import pe.edu.upeu.sysflex.mapper.ChoferMapper;
import pe.edu.upeu.sysflex.repository.ChoferRepository;
import pe.edu.upeu.sysflex.service.general.service.ChoferService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChoferServiceImpl implements ChoferService {

    private final ChoferRepository choferRepository;
    private final ChoferMapper choferMapper;

    @Override
    public List<ChoferDTO> obtenerDisponibles() {
        log.info("Obteniendo choferes disponibles");
        List<Chofer> choferes = choferRepository.findByEstado_Estado("Disponible");
        log.info("Se encontraron {} choferes disponibles", choferes.size());

        return choferes.stream()
                .map(choferMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChoferDTO> listarTodos() {
        log.info("Listando todos los choferes");
        List<Chofer> choferes = choferRepository.findAll();
        log.info("Se encontraron {} choferes", choferes.size());

        return choferes.stream()
                .map(choferMapper::toDTO)
                .collect(Collectors.toList());
    }
}