package pe.regioncusco.gob.simecr.modules.parametros.domain.services;

import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Eje;

import java.util.List;

public interface EjeService {
    List<MyValue> findAllSelection();
    Eje findEjeByid(Long id);
}
