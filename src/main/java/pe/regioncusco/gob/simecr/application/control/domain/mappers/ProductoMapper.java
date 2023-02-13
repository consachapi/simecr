package pe.regioncusco.gob.simecr.application.control.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.regioncusco.gob.simecr.application.control.domain.enums.ProductoEstado;
import pe.regioncusco.gob.simecr.application.control.domain.models.Producto;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.ProductoBodyDto;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.ProductoDto;
import pe.regioncusco.gob.simecr.core.common.MyValue;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(source = "presupuesto", target = "porcentaje", qualifiedByName = "calculePorcentaje")
    ProductoDto toProductoDto(Producto producto);
    default ProductoEstado map(Integer estado){
        return ProductoEstado.valueOf(estado);
    }

    @Named("calculePorcentaje")
    static Double calculePorcentaje(Double presupuesto){
        Double porcentGeneral = (presupuesto * 100) / 402094157.00;
        BigDecimal porcent = new BigDecimal(porcentGeneral).setScale(2, RoundingMode.HALF_UP);
        return porcent.doubleValue();
    }

    Producto toProducto(ProductoBodyDto productoBodyDto);

    @Mapping(source = "id", target = "value")
    @Mapping(source = "descripcion", target = "label")
    MyValue toMyValue(Producto producto);
}
