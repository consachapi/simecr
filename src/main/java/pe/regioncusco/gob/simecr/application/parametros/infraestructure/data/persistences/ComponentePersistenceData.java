package pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Eje;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Componente;
import pe.regioncusco.gob.simecr.application.parametros.domain.persistences.ComponentePersistence;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.commons.EjeCommon;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.ComponenteEntity;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.EjeEntity;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.repositorys.ComponenteEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ComponentePersistenceData implements ComponentePersistence {
    private static final Logger LOG = LoggerFactory.getLogger(ComponentePersistenceData.class);

    @Autowired private ComponenteEntityRepository componenteEntityRepository;

    @Override
    public List<Componente> findAll() {
        List<ComponenteEntity> componenteEntities = componenteEntityRepository.findAll();
        LOG.info("Componentes encontrados {}", componenteEntities);
        return componenteEntities.stream().map(componenteEntity -> componenteEntity.toComponente()).collect(Collectors.toList());
    }

    @Override
    public List<Componente> findAllByEje(Eje eje) {
        EjeEntity ejeEntity = EjeCommon.toEjeEntity(eje);
        List<ComponenteEntity> componenteEntities = componenteEntityRepository.findAllByEje(ejeEntity);
        return componenteEntities.stream().map(componenteEntity -> componenteEntity.toComponente()).collect(Collectors.toList());
    }

    @Override
    public Optional<Componente> findById(Long id) {
        Optional<ComponenteEntity> optionalComponenteEntity = componenteEntityRepository.findById(id);
        return optionalComponenteEntity.map(componenteEntity -> componenteEntity.toComponente());
    }
}
