package pe.regioncusco.gob.simecr.modules.parametros.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Componente;

@Mapper(componentModel = "spring")
public interface ComponenteMapper {
    @Mapping(source = "id", target = "value")
    @Mapping(source = "descripcion", target = "label")
    MyValue toMyValue(Componente componente);
}
