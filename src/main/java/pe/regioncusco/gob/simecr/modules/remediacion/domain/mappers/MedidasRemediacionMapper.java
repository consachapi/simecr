package pe.regioncusco.gob.simecr.modules.remediacion.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.enums.MedidasRemediacionEstado;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.MedidasRemediacionDto;

@Mapper(componentModel = "spring", uses = { DeficienciaMapper.class })
public interface MedidasRemediacionMapper {
    MedidasRemediacionDto toMedidasRemediacionDto(MedidasRemediacion medidasRemediacion);
    default MedidasRemediacionEstado map(Integer estado){
        return MedidasRemediacionEstado.valueOf(estado);
    }
}
