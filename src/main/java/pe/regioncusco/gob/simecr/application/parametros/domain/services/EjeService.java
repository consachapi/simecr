package pe.regioncusco.gob.simecr.application.parametros.domain.services;

import pe.regioncusco.gob.simecr.application.parametros.domain.models.Eje;
import pe.regioncusco.gob.simecr.core.common.MyValue;

import java.util.List;

public interface EjeService {
    List<MyValue> findAllSelection();
    Eje findEjeByid(Long id);
}
