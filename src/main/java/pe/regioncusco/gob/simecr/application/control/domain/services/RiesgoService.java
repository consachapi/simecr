package pe.regioncusco.gob.simecr.application.control.domain.services;

import pe.regioncusco.gob.simecr.application.control.domain.models.Riesgo;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.RiesgoDto;
import pe.regioncusco.gob.simecr.core.common.MyValue;

import java.util.List;

public interface RiesgoService {
    List<RiesgoDto> findAll();
    List<MyValue> findAllByProducto(Long producto);

    Riesgo save(Riesgo riesgo);
    Riesgo update(Long id, Riesgo riesgo);
    void delete(Long id);
    Riesgo findRiesgoById(Long id);
    RiesgoDto findByRiesgoDtoById(Long id);
}
