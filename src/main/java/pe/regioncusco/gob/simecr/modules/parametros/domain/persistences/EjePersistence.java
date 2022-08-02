package pe.regioncusco.gob.simecr.modules.parametros.domain.persistences;

import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Eje;

import java.util.List;
import java.util.Optional;

public interface EjePersistence {
    List<Eje> findAll();
    Optional<Eje> findById(Long id);
}
