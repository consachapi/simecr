package pe.regioncusco.gob.simecr.application.parametros.domain.persistences;

import pe.regioncusco.gob.simecr.application.parametros.domain.models.Eje;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Componente;

import java.util.List;
import java.util.Optional;

public interface ComponentePersistence {
    List<Componente> findAll();
    List<Componente> findAllByEje(Eje eje);
    Optional<Componente> findById(Long id);
}
