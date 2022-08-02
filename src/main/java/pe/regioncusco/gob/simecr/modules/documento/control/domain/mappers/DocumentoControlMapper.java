package pe.regioncusco.gob.simecr.modules.documento.control.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.modules.control.domain.mappers.ActividadControlMapper;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.enums.DocumentoEstado;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.models.DocumentoControl;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.models.dtos.DocumentoControlDto;

@Mapper(componentModel = "spring", uses = { ActividadControlMapper.class })
public interface DocumentoControlMapper {
    DocumentoControlDto toDocumentoControlDto(DocumentoControl documentoControl);
    default DocumentoEstado map(Integer estado){
        return DocumentoEstado.valueOf(estado);
    }
}
