package pe.regioncusco.gob.simecr.modules.control.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.ActividadEstado;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ActividadControlDto;

@Mapper(componentModel = "spring", uses = { ControlVerificacionMapper.class })
public interface ActividadControlMapper {
    ActividadControlDto toActividadControlDto(ActividadControl actividadControl);
    default ActividadEstado map(Integer estado){
        return ActividadEstado.valueOf(estado);
    }
}
