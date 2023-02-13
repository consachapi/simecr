package pe.regioncusco.gob.simecr.application.parametros.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Eje;
import pe.regioncusco.gob.simecr.core.common.MyValue;

@Mapper(componentModel = "spring")
public interface EjeMapper {
    @Mapping(source = "id", target = "value")
    @Mapping(source = "descripcion", target = "label")
    MyValue toMyValue(Eje eje);
}
