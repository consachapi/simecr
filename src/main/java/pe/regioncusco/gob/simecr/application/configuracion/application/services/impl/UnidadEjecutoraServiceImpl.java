package pe.regioncusco.gob.simecr.application.configuracion.application.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.core.common.MyValueString;
import pe.regioncusco.gob.simecr.core.enums.Status;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.configuracion.domain.models.UnidadEjecutoria;
import pe.regioncusco.gob.simecr.application.configuracion.application.persistences.UnidadEjecutoraPersistence;
import pe.regioncusco.gob.simecr.application.configuracion.application.services.UnidadEjecutoraService;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadEjecutoraServiceImpl implements UnidadEjecutoraService {
    private static final Logger LOG = LoggerFactory.getLogger(UnidadEjecutoraServiceImpl.class);

    @Autowired private UnidadEjecutoraPersistence unidadEjecutoraPersistence;

    @Override
    public List<UnidadEjecutoria> findAll() {
        return unidadEjecutoraPersistence.findAll();
    }

    @Override
    public List<UnidadEjecutoria> findAllActive() {
        List<UnidadEjecutoria> unidadEjecutorias = unidadEjecutoraPersistence.findAllEnabled();
        LOG.info("Procesando unidad ejec activas.");
        return unidadEjecutorias;
    }

    @Override
    public UnidadEjecutoria findUnidadEjecutoraById(String id) {
        return findById(id);
    }

    @Override
    public UnidadEjecutoria create(UnidadEjecutoria unidadEjecutoria) {
        LOG.info("Creando Unidad Ejec. {}", unidadEjecutoria.getCodigo());
        return unidadEjecutoraPersistence.create(unidadEjecutoria);
    }

    @Override
    public UnidadEjecutoria update(String id, UnidadEjecutoria unidadEjecutoria) {
        return unidadEjecutoraPersistence.update(id, unidadEjecutoria);
    }

    @Override
    public void disabled(String id) {
        UnidadEjecutoria unidadEjecutoria = findById(id);
        unidadEjecutoraPersistence.disabled(unidadEjecutoria, Status.DISABLED.value());
    }

    @Override
    public void enabled(String id) {
        UnidadEjecutoria unidadEjecutoria = findById(id);
        unidadEjecutoraPersistence.disabled(unidadEjecutoria, Status.ENABLED.value());
    }

    private UnidadEjecutoria findById(String id){
        Optional<UnidadEjecutoria> optional = unidadEjecutoraPersistence.findById(id);
        if(!optional.isPresent()){
            LOG.error("No existe Unidad Ejecutora con Id {}", id);
            throw new NotFoundException("No existe Unidad Ejecutora "+ id);
        }
        LOG.info("Unidad Ejecutora encontrado con Id {}", id);
        return optional.get();
    }

}
