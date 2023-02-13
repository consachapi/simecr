package pe.regioncusco.gob.simecr.application.documento.control.infraestructure.data.commons;

import pe.regioncusco.gob.simecr.application.documento.control.infraestructure.data.entities.DocumentoControlEntity;
import pe.regioncusco.gob.simecr.application.documento.control.domain.models.DocumentoControl;

public final class DocumentoControlAdapterCommon {
    public static DocumentoControlEntity toDocumentoControlEntity(DocumentoControl documentoControl){
        DocumentoControlEntity documentoControlEntity = new DocumentoControlEntity();
        return documentoControlEntity;
    }
}
