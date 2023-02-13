package pe.regioncusco.gob.simecr.modules.control.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.ControlVerificacionEstado;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ControlVerificacionDto;

@Mapper(componentModel = "spring", uses = { MedidasControlMapper.class })
public interface ControlVerificacionMapper {
    ControlVerificacionDto toControlVerificacionDto(ControlVerificacion controlVerificacion);
    default ControlVerificacionEstado map(Integer estado){
        return ControlVerificacionEstado.valueOf(estado);
    }
}
