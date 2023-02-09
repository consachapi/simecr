package pe.regioncusco.gob.simecr.modules.configuracion.domain.services;

import pe.regioncusco.gob.simecr.commons.MyValueString;
import pe.regioncusco.gob.simecr.modules.configuracion.domain.models.UnidadEjecutoria;

import java.util.List;

public interface UnidadEjecutoraService {
    List<UnidadEjecutoria> findAll();
    List<UnidadEjecutoria> findAllActive();
    List<MyValueString> findByIdSelection(String codigo);
    UnidadEjecutoria findUnidadEjecutoraById(String id);
}
