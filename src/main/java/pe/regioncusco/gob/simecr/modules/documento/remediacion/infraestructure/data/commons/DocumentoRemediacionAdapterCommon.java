package pe.regioncusco.gob.simecr.modules.documento.remediacion.infraestructure.data.commons;

import pe.regioncusco.gob.simecr.modules.documento.remediacion.infraestructure.data.entities.DocumentoRemediacionEntity;

public final class DocumentoRemediacionAdapterCommon {
    public static DocumentoRemediacionEntity toDocumentoRemediacionEntity(){
        DocumentoRemediacionEntity documentoRemediacionEntity = new DocumentoRemediacionEntity();
        return documentoRemediacionEntity;
    }
}
