package pe.regioncusco.gob.simecr.application.remediacion.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.application.control.domain.enums.ActividadEstado;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.ActividadRemediacionDto;

@Mapper(componentModel = "spring", uses = { RemediacionVerificacionMapper.class })
public interface ActividadRemediacionMapper {
    ActividadRemediacionDto toActividadRemediacionDto(ActividadRemediacion actividadRemediacion);
    default ActividadEstado map(Integer estado){
        return ActividadEstado.valueOf(estado);
    }
}
