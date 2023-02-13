package pe.regioncusco.gob.simecr.security.domains.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.security.domains.models.Perfil;

@Mapper(componentModel = "spring")
public interface PerfilMapper {
    @Mapping(source = "id", target = "value")
    @Mapping(source = "descripcion", target = "label")
    MyValue toMyValue(Perfil perfil);
}
