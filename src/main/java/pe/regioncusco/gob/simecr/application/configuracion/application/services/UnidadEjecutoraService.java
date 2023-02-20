package pe.regioncusco.gob.simecr.application.configuracion.application.services;

import pe.regioncusco.gob.simecr.core.common.MyValueString;
import pe.regioncusco.gob.simecr.application.configuracion.domain.models.UnidadEjecutoria;

import java.util.List;

public interface UnidadEjecutoraService {
    List<UnidadEjecutoria> findAll();
    List<UnidadEjecutoria> findAllActive();
    UnidadEjecutoria findUnidadEjecutoraById(String id);
    UnidadEjecutoria create(UnidadEjecutoria unidadEjecutoria);
    UnidadEjecutoria update(String id, UnidadEjecutoria unidadEjecutoria);
    void disabled(String id);
    void enabled(String id);
}
