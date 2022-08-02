package pe.regioncusco.gob.simecr.modules.remediacion.domain.services;

import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.Deficiencia;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.DeficienciaBodyDto;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.DeficienciaDto;

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
