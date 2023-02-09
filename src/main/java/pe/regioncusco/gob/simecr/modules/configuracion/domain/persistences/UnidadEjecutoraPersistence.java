package pe.regioncusco.gob.simecr.modules.configuracion.domain.persistences;

import pe.regioncusco.gob.simecr.modules.configuracion.domain.models.UnidadEjecutoria;

import java.util.List;
import java.util.Optional;

public interface UnidadEjecutoraPersistence {
    List<UnidadEjecutoria> findAllEnabled();
    List<UnidadEjecutoria> findAll();
    UnidadEjecutoria create(UnidadEjecutoria unidadEjecutoria);
    Optional<UnidadEjecutoria> findById(String id);
    UnidadEjecutoria update(String id, UnidadEjecutoria unidadEjecutoria);
    void disabled(String id, boolean enabled);
}
