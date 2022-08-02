package pe.regioncusco.gob.simecr.security.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.security.domain.models.Cargo;

@Mapper(componentModel = "spring")
public interface CargoMapper {
    @Mapping(source = "id", target = "value")
    @Mapping(source = "descripcion", target = "label")
    MyValue toMyValue(Cargo cargo);
}
