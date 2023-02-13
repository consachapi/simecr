package pe.regioncusco.gob.simecr.modules.control.domain.services;

import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.modules.control.domain.models.Producto;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ProductoBodyDto;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ProductoDto;

import java.util.List;

public interface ProductoService {
    List<ProductoDto> findAll();
    List<MyValue> findAllActive();
    ProductoDto save(ProductoBodyDto producto);
    ProductoDto update(Long id, ProductoBodyDto producto);
    ProductoDto findById(Long id);
    Producto findProductoById(Long id);
    void delete(Long id);
}
