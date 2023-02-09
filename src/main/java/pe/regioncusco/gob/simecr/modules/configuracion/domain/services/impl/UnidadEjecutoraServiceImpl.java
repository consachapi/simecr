package pe.regioncusco.gob.simecr.modules.configuracion.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.commons.MyValueString;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.modules.configuracion.domain.models.UnidadEjecutoria;
import pe.regioncusco.gob.simecr.modules.configuracion.domain.persistences.UnidadEjecutoraPersistence;
import pe.regioncusco.gob.simecr.modules.configuracion.domain.services.UnidadEjecutoraService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<MyValueString> findByIdSelection(String codigo) {
        UnidadEjecutoria unidadEjecutoria = findById(codigo);
        List<MyValueString> myValueStrings = new ArrayList<>();
        myValueStrings.add(new MyValueString(unidadEjecutoria.getCodigo(), unidadEjecutoria.getDescripcion()));
        return myValueStrings;
    }

    @Override
    public UnidadEjecutoria findUnidadEjecutoraById(String id) {
        return findById(id);
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
