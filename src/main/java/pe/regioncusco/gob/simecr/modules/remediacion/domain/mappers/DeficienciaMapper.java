package pe.regioncusco.gob.simecr.modules.remediacion.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.enums.DeficienciaEstado;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.Deficiencia;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.DeficienciaDto;

@Mapper(componentModel = "spring")
public interface DeficienciaMapper {
    DeficienciaDto toDeficienciaDto(Deficiencia deficiencia);
    default DeficienciaEstado map(Integer estado){
        return DeficienciaEstado.valueOf(estado);
    }

    @Mapping(source = "id", target = "value")
    @Mapping(source = "deficiencia", target = "label")
    MyValue toMyValue(Deficiencia deficiencia);
}
