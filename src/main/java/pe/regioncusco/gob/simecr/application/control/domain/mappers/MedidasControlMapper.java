package pe.regioncusco.gob.simecr.application.control.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.application.control.domain.enums.MedidasControlEstado;
import pe.regioncusco.gob.simecr.application.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.MedidasControlDto;

@Mapper(componentModel = "spring", uses = {RiesgoMapper.class})
public interface MedidasControlMapper {
    MedidasControlDto toMedidasControlDto(MedidasControl medidasControl);
    default MedidasControlEstado map(Integer estado){
        return MedidasControlEstado.valueOf(estado);
    }

}
