package pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.persistences;

import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.models.DocumentoRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;

import java.util.List;
import java.util.Optional;

public interface DocumentoRemediacionPersistence {
    List<DocumentoRemediacion> findAll();
    List<DocumentoRemediacion> findAllByActividad(ActividadRemediacion actividadRemediacion);
    DocumentoRemediacion create(DocumentoRemediacion documentoRemediacion, Persona usuario);
    DocumentoRemediacion update(DocumentoRemediacion documentoRemediacion, Integer estado, String usuario);
    Optional<DocumentoRemediacion> findById(Long id);
    void delete(DocumentoRemediacion documentoRemediacion);
}
