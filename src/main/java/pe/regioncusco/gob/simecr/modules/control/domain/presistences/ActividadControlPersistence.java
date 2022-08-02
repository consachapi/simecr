package pe.regioncusco.gob.simecr.modules.control.domain.presistences;

import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;

import java.util.List;
import java.util.Optional;

public interface ActividadControlPersistence {
    ActividadControl save(ActividadControl actividadControl, String usuario, Integer estado);
    List<ActividadControl> findAllByControlVerificacion(ControlVerificacion controlVerificacion);
    void delete(ActividadControl actividadControl);
    Optional<ActividadControl> findById(Long id);
    ActividadControl finalize(ActividadControl actividadControl, Integer estado);
}
