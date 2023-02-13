package pe.regioncusco.gob.simecr.application.remediacion.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.RemediacionVerificacionDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.enums.RemediacionVerificacionEstado;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.RemediacionVerificacion;

@Mapper(componentModel = "spring", uses = { MedidasRemediacionMapper.class })
public interface RemediacionVerificacionMapper {
    RemediacionVerificacionDto toRemediacionVerificacionDto(RemediacionVerificacion remediacionVerificacion);
    default RemediacionVerificacionEstado map(Integer estado){
        return RemediacionVerificacionEstado.valueOf(estado);
    }
}
