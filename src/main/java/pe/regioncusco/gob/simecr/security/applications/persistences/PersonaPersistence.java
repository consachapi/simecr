package pe.regioncusco.gob.simecr.security.applications.persistences;

import org.springframework.data.domain.Page;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;

import java.util.Optional;

public interface PersonaPersistence {
    Page<Persona> findAll(int page, int size);
    Page<Persona> findByNumeroDocumentoContains(String termino, int page, int size);
    Optional<Persona> findByNdocumento(String numero);
}
