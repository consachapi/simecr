package pe.regioncusco.gob.simecr.application.remediacion.domain.persistences;

import pe.regioncusco.gob.simecr.application.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.RemediacionVerificacion;

import java.util.List;
import java.util.Optional;

public interface ActividadRemediacionPersistence {
    ActividadRemediacion save(ActividadRemediacion actividadRemediacion, String usuario, Integer estado);
    List<ActividadRemediacion> findAllByRemediacionVerificacion(RemediacionVerificacion remediacionVerificacion);
    void delete(ActividadRemediacion actividadRemediacion);
    Optional<ActividadRemediacion> findById(Long id);
    ActividadRemediacion finalize(ActividadRemediacion actividadRemediacion, Integer estado);
}
