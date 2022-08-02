package pe.regioncusco.gob.simecr.modules.remediacion.domain.persistences;

import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.RemediacionVerificacion;

import java.util.List;
import java.util.Optional;

public interface RemediacionVerificacionPersistence {
    RemediacionVerificacion save(RemediacionVerificacion remediacionVerificacion, String usuario, Integer estado);
    RemediacionVerificacion updateOficina(RemediacionVerificacion remediacionVerificacion);
    List<RemediacionVerificacion> findAll();
    List<RemediacionVerificacion> findAllByMedidasRemediacion(MedidasRemediacion medidasRemediacion);
    Optional<RemediacionVerificacion> findById(Long id);
    List<RemediacionVerificacion> findAllByOficinas(List<Oficina> oficinas);
}
