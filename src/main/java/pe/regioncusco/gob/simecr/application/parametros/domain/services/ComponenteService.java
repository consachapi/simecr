package pe.regioncusco.gob.simecr.modules.parametros.domain.services;

import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Componente;

import java.util.List;

public interface ComponenteService {
    List<MyValue> findAllSelectionByEje(Long id);
    Componente findComponenteById(Long id);
}
