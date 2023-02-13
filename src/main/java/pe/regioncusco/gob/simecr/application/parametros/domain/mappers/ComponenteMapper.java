package pe.regioncusco.gob.simecr.application.parametros.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Componente;

@Mapper(componentModel = "spring")
public interface ComponenteMapper {
    @Mapping(source = "id", target = "value")
    @Mapping(source = "descripcion", target = "label")
    MyValue toMyValue(Componente componente);
}
