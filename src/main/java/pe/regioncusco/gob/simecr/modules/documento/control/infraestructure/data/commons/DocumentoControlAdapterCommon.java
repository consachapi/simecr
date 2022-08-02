package pe.regioncusco.gob.simecr.modules.documento.control.infraestructure.data.commons;

import pe.regioncusco.gob.simecr.modules.documento.control.domain.models.DocumentoControl;
import pe.regioncusco.gob.simecr.modules.documento.control.infraestructure.data.entities.DocumentoControlEntity;

public final class DocumentoControlAdapterCommon {
    public static DocumentoControlEntity toDocumentoControlEntity(DocumentoControl documentoControl){
        DocumentoControlEntity documentoControlEntity = new DocumentoControlEntity();
        return documentoControlEntity;
    }
}
