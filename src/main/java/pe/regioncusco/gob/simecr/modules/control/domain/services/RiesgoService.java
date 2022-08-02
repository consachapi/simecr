package pe.regioncusco.gob.simecr.modules.control.domain.services;

import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.modules.control.domain.models.Riesgo;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.RiesgoDto;

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
