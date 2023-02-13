package pe.regioncusco.gob.simecr.application.remediacion.domain.services;

import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.Deficiencia;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.DeficienciaBodyDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.DeficienciaDto;

import java.util.List;

public interface DeficienciaService {
    DeficienciaDto save(DeficienciaBodyDto deficienciaBodyDto);
    List<DeficienciaDto> findAll();
    DeficienciaDto show(Long id);
    DeficienciaDto update(Long id, DeficienciaBodyDto deficienciaBodyDto);
    void delete(Long id);
    List<MyValue> selection();
    Deficiencia findDeficienciaById(Long id);
}
