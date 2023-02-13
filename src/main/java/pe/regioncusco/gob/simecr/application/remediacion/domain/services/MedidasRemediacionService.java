package pe.regioncusco.gob.simecr.application.remediacion.domain.services;

import pe.regioncusco.gob.simecr.application.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.MedidasRemediacionDto;

import java.util.List;

public interface MedidasRemediacionService {
    MedidasRemediacionDto save(MedidasRemediacion medidasRemediacion);
    MedidasRemediacionDto update(Long id, MedidasRemediacion medidasRemediacion);
    List<MedidasRemediacionDto> findAll();
    MedidasRemediacionDto find(Long id);
    MedidasRemediacion findMedidasRemediacionById(Long id);
}
