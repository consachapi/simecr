package pe.regioncusco.gob.simecr.application.parametros.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Eje;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.parametros.domain.mappers.EjeMapper;
import pe.regioncusco.gob.simecr.application.parametros.domain.persistences.EjePersistence;
import pe.regioncusco.gob.simecr.application.parametros.domain.services.EjeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EjeServiceImpl implements EjeService {
    private static final Logger LOG = LoggerFactory.getLogger(EjeServiceImpl.class);

    @Autowired private EjePersistence ejePersistence;
    @Autowired private EjeMapper ejeMapper;

    @Override
    public List<MyValue> findAllSelection() {
        List<Eje> ejes = ejePersistence.findAll();
        LOG.info("Ejes encontrados {}", ejes.size());
        return ejes.stream().map(eje -> ejeMapper.toMyValue(eje)).collect(Collectors.toList());
    }

    @Override
    public Eje findEjeByid(Long id) {
        return findById(id);
    }

    private Eje findById(Long id){
        Optional<Eje> optional = ejePersistence.findById(id);
        if(!optional.isPresent()){
            LOG.error("No existe eje con id {}", id);
            throw new NotFoundException("No existe Eje");
        }
        return optional.get();
    }
}
