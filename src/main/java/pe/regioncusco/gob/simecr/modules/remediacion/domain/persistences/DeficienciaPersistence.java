package pe.regioncusco.gob.simecr.modules.remediacion.domain.persistences;

import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.Deficiencia;

import java.util.List;
import java.util.Optional;

public interface DeficienciaPersistence {
    Deficiencia save(Deficiencia deficiencia, String usuario, Integer estado);
    List<Deficiencia> findAll();
    List<Deficiencia> findAllByEstado(Integer estado);
    Optional<Deficiencia> findById(Long id);
}
