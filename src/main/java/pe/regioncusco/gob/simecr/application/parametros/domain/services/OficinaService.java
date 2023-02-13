package pe.regioncusco.gob.simecr.application.parametros.domain.services;

import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.core.common.MyValue;

import java.util.List;

public interface OficinaService {
    List<MyValue> findAllSelection();
    Oficina findByAbreviatura(String abreviatura);
}
