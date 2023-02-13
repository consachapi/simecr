package pe.regioncusco.gob.simecr.application.control.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.control.domain.models.Producto;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.ProductoEntity;

public final class ProductoAdapterCommon {
    public static ProductoEntity toProductoEntity(Producto producto){
        ProductoEntity productoEntity =  new ProductoEntity();
        BeanUtils.copyProperties(producto, productoEntity);
        return productoEntity;
    }
}
