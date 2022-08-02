package pe.regioncusco.gob.simecr.modules.parametros.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.modules.parametros.domain.mappers.OficinaMapper;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.parametros.domain.persistences.OficinaPersistence;
import pe.regioncusco.gob.simecr.modules.parametros.domain.services.OficinaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OficinaServiceImpl implements OficinaService {
    private static final Logger LOG = LoggerFactory.getLogger(OficinaServiceImpl.class);

    @Autowired private OficinaPersistence oficinaPersistence;
    @Autowired private OficinaMapper oficinaMapper;

    @Override
    public List<MyValue> findAllSelection() {
        List<Oficina> oficinas = oficinaPersistence.findAll();
        LOG.info("Oficinas encontradas {}", oficinas.size());
        return oficinas.stream().map(oficina -> oficinaMapper.toMyValue(oficina)).collect(Collectors.toList());
    }

    @Override
    public Oficina findByAbreviatura(String abreviatura) {
        Optional<Oficina> optionalOficina = oficinaPersistence.findByAbreviatura(abreviatura);
        return optionalOficina.orElseThrow(() -> new NotFoundException("No existe Oficina con la abreviatura " + abreviatura));
    }
}
