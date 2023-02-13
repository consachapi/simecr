package pe.regioncusco.gob.simecr.security.domain.services;

import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.security.domains.models.Cargo;

import java.util.List;

public interface CargoService {
    List<MyValue> findAllSelection();
    Cargo findCargoById(Long id);
}
