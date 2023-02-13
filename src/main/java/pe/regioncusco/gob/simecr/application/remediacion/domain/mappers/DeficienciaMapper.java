package pe.regioncusco.gob.simecr.application.remediacion.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.application.remediacion.domain.enums.DeficienciaEstado;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.Deficiencia;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.DeficienciaDto;

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
