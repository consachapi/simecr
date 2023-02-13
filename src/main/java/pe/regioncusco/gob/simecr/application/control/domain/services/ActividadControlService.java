package pe.regioncusco.gob.simecr.modules.control.domain.services;

import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ActividadControlBodyDto;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ActividadControlDto;

import java.util.List;

public interface ActividadControlService {
    ActividadControlDto save(Long id, ActividadControlBodyDto actividad);
    List<ActividadControlDto> findAllByControlVerificacion(Long id);
    void delete(Long id);
    ActividadControl findActividadControlById(Long id);
    ActividadControlDto finalize(Long id);
}
