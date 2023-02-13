package pe.regioncusco.gob.simecr.application.control.domain.presistences;

import pe.regioncusco.gob.simecr.application.control.domain.models.Producto;
import pe.regioncusco.gob.simecr.application.control.domain.models.Riesgo;

import java.util.List;
import java.util.Optional;

public interface RiesgoPersistence {
    List<Riesgo> findAll();
    List<Riesgo> findAllByProducto(Producto producto);
    Riesgo save(Riesgo riesgo, String usuario, Integer periodo, Integer estado);
    void delete(Riesgo riesgo, String usuario, Integer estado);
    Optional<Riesgo> findById(Long id);
}
