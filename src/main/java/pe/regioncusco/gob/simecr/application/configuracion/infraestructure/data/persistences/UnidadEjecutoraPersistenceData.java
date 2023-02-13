package pe.regioncusco.gob.simecr.application.configuracion.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.regioncusco.gob.simecr.application.configuracion.domain.models.UnidadEjecutoria;
import pe.regioncusco.gob.simecr.application.configuracion.application.persistences.UnidadEjecutoraPersistence;
import pe.regioncusco.gob.simecr.application.configuracion.infraestructure.data.entities.UnidadEjecutoriaEntity;
import pe.regioncusco.gob.simecr.application.configuracion.infraestructure.data.respositorys.UnidadEjecutoriaEntityRepository;
import pe.regioncusco.gob.simecr.core.enums.Status;
import pe.regioncusco.gob.simecr.application.configuracion.infraestructure.data.commons.UnidadEjecutoraCommonData;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class UnidadEjecutoraPersistenceData implements UnidadEjecutoraPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(UnidadEjecutoraPersistenceData.class);

    @Autowired private UnidadEjecutoriaEntityRepository unidadEjecutoriaEntityRepository;

    @Override
    public List<UnidadEjecutoria> findAllEnabled() {
        List<UnidadEjecutoriaEntity> unidadEjecutoriaEntities = unidadEjecutoriaEntityRepository.findAllByEnabled(Status.ENABLED.value());
        LOG.info("Total de ejecutoras activas {}", unidadEjecutoriaEntities.size());
        return unidadEjecutoriaEntities.stream().map(UnidadEjecutoriaEntity::toUnidadEjecutoria).collect(Collectors.toList());
    }

    @Override
    public List<UnidadEjecutoria> findAll() {
        List<UnidadEjecutoriaEntity> unidadEjecutoriaEntities = unidadEjecutoriaEntityRepository.findAll();
        LOG.info("Total de ejecutoras {}", unidadEjecutoriaEntities.size());
        return unidadEjecutoriaEntities.stream().map(UnidadEjecutoriaEntity::toUnidadEjecutoria).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UnidadEjecutoria create(UnidadEjecutoria unidadEjecutoria) {
        UnidadEjecutoriaEntity unidadEjecutoriaEntity = UnidadEjecutoraCommonData.toEntity(unidadEjecutoria);
        return unidadEjecutoriaEntityRepository.save(unidadEjecutoriaEntity).toUnidadEjecutoria();
    }

    @Override
    public Optional<UnidadEjecutoria> findById(String id) {
        Optional<UnidadEjecutoriaEntity> optional = unidadEjecutoriaEntityRepository.findById(id);
        LOG.info("Is present Unidad Ejecutora Entity {}, {}", id, optional.isPresent());
        return optional.map(UnidadEjecutoriaEntity::toUnidadEjecutoria);
    }

    @Override
    public UnidadEjecutoria update(String id, UnidadEjecutoria unidadEjecutoria) {
        UnidadEjecutoriaEntity unidadEjecutoriaEntity = unidadEjecutoriaEntityRepository.findById(id).get();
        unidadEjecutoriaEntity.setDescripcion(unidadEjecutoria.getDescripcion());
        unidadEjecutoriaEntity.setEnabled(unidadEjecutoria.isEnabled());
        LOG.info("Modificando Unidad Ejecutora Entity {}", unidadEjecutoriaEntity.getCodigo());
        return unidadEjecutoriaEntityRepository.save(unidadEjecutoriaEntity).toUnidadEjecutoria();
    }

    @Override
    public void disabled(String id, boolean enabled) {
        UnidadEjecutoriaEntity unidadEjecutoriaEntity = unidadEjecutoriaEntityRepository.findById(id).get();
        unidadEjecutoriaEntity.setEnabled(enabled);
        unidadEjecutoriaEntityRepository.save(unidadEjecutoriaEntity);
    }


}
