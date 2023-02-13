package pe.regioncusco.gob.simecr.application.control.domain.services;

import pe.regioncusco.gob.simecr.application.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.MedidasControlDto;

import java.util.List;

public interface MedidasControlService {
    MedidasControlDto save(MedidasControl medidasControl);
    MedidasControlDto update(Long id, MedidasControl medidasControl);
    List<MedidasControlDto> findAll();
    void delete(Long id);
    MedidasControlDto findMedidaControlById(Long id);
    MedidasControl findMedidaById(Long id);
}
