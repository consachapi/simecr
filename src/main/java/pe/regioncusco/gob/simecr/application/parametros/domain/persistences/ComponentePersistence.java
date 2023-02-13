package pe.regioncusco.gob.simecr.modules.parametros.domain.persistences;

import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Componente;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Eje;

import java.util.List;
import java.util.Optional;

public interface ComponentePersistence {
    List<Componente> findAll();
    List<Componente> findAllByEje(Eje eje);
    Optional<Componente> findById(Long id);
}
