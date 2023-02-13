package pe.regioncusco.gob.simecr.application.control.domain.presistences;

import pe.regioncusco.gob.simecr.application.control.domain.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoPersistence {
    List<Producto> findAll();
    List<Producto> findAllByActive(Integer estado);
    Producto save(Producto producto, String usuario, Integer estado);
    Optional findById(Long id);
}
