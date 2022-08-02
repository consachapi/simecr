package pe.regioncusco.gob.simecr.modules.remediacion.domain.services;

import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.ActividadRemediacionBodyDto;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.ActividadRemediacionDto;

import java.util.List;

public interface ActividadRemediacionService {
    ActividadRemediacionDto save(Long id, ActividadRemediacionBodyDto actividad);
    List<ActividadRemediacionDto> findAllByRemediacionVerificacion(Long id);
    void delete(Long id);
    ActividadRemediacion findActividadRemediacionById(Long id);
    ActividadRemediacionDto finalize(Long id);
}
