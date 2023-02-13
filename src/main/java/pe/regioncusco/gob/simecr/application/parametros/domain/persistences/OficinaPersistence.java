package pe.regioncusco.gob.simecr.application.parametros.domain.persistences;

import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;

import java.util.List;
import java.util.Optional;

public interface OficinaPersistence {
    List<Oficina> findAll();
    Optional<Oficina> findByAbreviatura(String abreviatura);
}
