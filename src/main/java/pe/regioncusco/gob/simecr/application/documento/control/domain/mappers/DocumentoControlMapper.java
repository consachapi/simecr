package pe.regioncusco.gob.simecr.application.documento.control.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.application.control.domain.mappers.ActividadControlMapper;
import pe.regioncusco.gob.simecr.application.documento.control.domain.models.dtos.DocumentoControlDto;
import pe.regioncusco.gob.simecr.application.documento.control.domain.enums.DocumentoEstado;
import pe.regioncusco.gob.simecr.application.documento.control.domain.models.DocumentoControl;

@Mapper(componentModel = "spring", uses = { ActividadControlMapper.class })
public interface DocumentoControlMapper {
    DocumentoControlDto toDocumentoControlDto(DocumentoControl documentoControl);
    default DocumentoEstado map(Integer estado){
        return DocumentoEstado.valueOf(estado);
    }
}
