package pe.regioncusco.gob.simecr.application.remediacion.domain.services;

import pe.regioncusco.gob.simecr.application.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.ActividadRemediacionBodyDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.ActividadRemediacionDto;

import java.util.List;

public interface ActividadRemediacionService {
    ActividadRemediacionDto save(Long id, ActividadRemediacionBodyDto actividad);
    List<ActividadRemediacionDto> findAllByRemediacionVerificacion(Long id);
    void delete(Long id);
    ActividadRemediacion findActividadRemediacionById(Long id);
    ActividadRemediacionDto finalize(Long id);
}
