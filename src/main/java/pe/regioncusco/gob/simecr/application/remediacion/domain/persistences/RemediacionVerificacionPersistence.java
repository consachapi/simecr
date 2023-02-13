package pe.regioncusco.gob.simecr.application.remediacion.domain.persistences;

import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.RemediacionVerificacion;

import java.util.List;
import java.util.Optional;

public interface RemediacionVerificacionPersistence {
    RemediacionVerificacion save(RemediacionVerificacion remediacionVerificacion, String usuario, Integer estado);
    RemediacionVerificacion updateOficina(RemediacionVerificacion remediacionVerificacion);
    List<RemediacionVerificacion> findAll();
    List<RemediacionVerificacion> findAllByMedidasRemediacion(MedidasRemediacion medidasRemediacion);
    Optional<RemediacionVerificacion> findById(Long id);
    List<RemediacionVerificacion> findAllByOficinas(List<Oficina> oficinas);
    RemediacionVerificacion changeEstado(RemediacionVerificacion remediacionVerificacion, Integer estado, String username);
}
