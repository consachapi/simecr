package pe.regioncusco.gob.simecr.modules.control.domain.commons;

import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ProductoEntity;
import pe.regioncusco.gob.simecr.modules.control.domain.models.Producto;

import java.util.List;
import java.util.stream.Collectors;

public final class ProductoDomainCommon {
    public static List<Producto> toListProducto(List<ProductoEntity> productoEntities){
        return productoEntities.stream().map(productoEntity -> productoEntity.toProducto()).collect(Collectors.toList());
    }
}
