package pe.regioncusco.gob.simecr.modules.parametros.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.modules.parametros.domain.mappers.ComponenteMapper;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Componente;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Eje;
import pe.regioncusco.gob.simecr.modules.parametros.domain.persistences.ComponentePersistence;
import pe.regioncusco.gob.simecr.modules.parametros.domain.services.ComponenteService;
import pe.regioncusco.gob.simecr.modules.parametros.domain.services.EjeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComponenteServiceImpl implements ComponenteService {
    private static final Logger LOG = LoggerFactory.getLogger(ComponenteServiceImpl.class);

    @Autowired private ComponentePersistence componentePersistence;
    @Autowired private ComponenteMapper componenteMapper;
    @Autowired private EjeService ejeService;

    @Override
    public List<MyValue> findAllSelectionByEje(Long id) {
        Eje eje = ejeService.findEjeByid(id);
        List<Componente> componentes = componentePersistence.findAllByEje(eje);
        LOG.info("Componentes del eje {}", eje.getDescripcion());
        return componentes.stream().map(componente -> componenteMapper.toMyValue(componente)).collect(Collectors.toList());
    }

    @Override
    public Componente findComponenteById(Long id) {
        return findById(id);
    }

    private Componente findById(Long id){
        Optional<Componente> optionalComponente = componentePersistence.findById(id);
        if(!optionalComponente.isPresent()){
            LOG.error("El componente {}, no existe", id);
            throw new NotFoundException("No existe componente");
        }
        return optionalComponente.get();
    }

}
