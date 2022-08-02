package pe.regioncusco.gob.simecr.modules.remediacion.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.enums.RemediacionVerificacionEstado;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.RemediacionVerificacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.RemediacionVerificacionDto;

@Mapper(componentModel = "spring", uses = { MedidasRemediacionMapper.class })
public interface RemediacionVerificacionMapper {
    RemediacionVerificacionDto toRemediacionVerificacionDto(RemediacionVerificacion remediacionVerificacion);
    default RemediacionVerificacionEstado map(Integer estado){
        return RemediacionVerificacionEstado.valueOf(estado);
    }
}
