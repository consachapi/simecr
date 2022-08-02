package pe.regioncusco.gob.simecr.modules.remediacion.domain.persistences;

import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.MedidasRemediacion;

import java.util.List;
import java.util.Optional;

public interface MedidasRemediacionPersistencia {
    MedidasRemediacion save(MedidasRemediacion medidasRemediacion, String usuario, Integer estado);
    MedidasRemediacion update(MedidasRemediacion medidasRemediacion, Integer estado);
    List<MedidasRemediacion> findAll();
    Optional<MedidasRemediacion> findById(Long id);
}
