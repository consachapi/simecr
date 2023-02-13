package pe.regioncusco.gob.simecr.application.documento.remediacion.domain.mappers;

import org.mapstruct.Mapper;
import pe.regioncusco.gob.simecr.application.documento.control.domain.enums.DocumentoEstado;
import pe.regioncusco.gob.simecr.application.documento.remediacion.domain.models.DocumentoRemediacion;
import pe.regioncusco.gob.simecr.application.documento.remediacion.domain.models.dtos.DocumentoRemediacionDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.mappers.ActividadRemediacionMapper;

@Mapper(componentModel = "spring", uses = { ActividadRemediacionMapper.class })
public interface DocumentoRemediacionMapper {
    DocumentoRemediacionDto toDocumentoRemediacionDto(DocumentoRemediacion documentoRemediacion);
    default DocumentoEstado map(Integer estado){
        return DocumentoEstado.valueOf(estado);
    }
}
