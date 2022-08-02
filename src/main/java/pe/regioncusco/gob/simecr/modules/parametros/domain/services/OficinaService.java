package pe.regioncusco.gob.simecr.modules.parametros.domain.services;

import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;

import java.util.List;

public interface OficinaService {
    List<MyValue> findAllSelection();
    Oficina findByAbreviatura(String abreviatura);
}
