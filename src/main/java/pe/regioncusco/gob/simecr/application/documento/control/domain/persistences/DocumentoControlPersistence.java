package pe.regioncusco.gob.simecr.application.documento.control.domain.persistences;

import pe.regioncusco.gob.simecr.application.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.application.documento.control.domain.models.DocumentoControl;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;

import java.util.List;
import java.util.Optional;

public interface DocumentoControlPersistence {
    List<DocumentoControl> findAll();
    List<DocumentoControl> findAllByActividad(ActividadControl actividadControl);
    DocumentoControl create(DocumentoControl documentoControl, Persona usuario);
    DocumentoControl update(DocumentoControl documentoControl, Integer estado, String usuario);
    Optional<DocumentoControl> findById(Long id);
    void delete(DocumentoControl documentoControl);
}
