package pe.regioncusco.gob.simecr.security.domain.services;

import org.springframework.data.domain.Page;
import pe.regioncusco.gob.simecr.security.domains.dtos.PersonaBodyDto;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;

public interface PersonaService {
    Page<Persona> findAll(int page, int size);
    Page<Persona> findByNumeroDocumentoContains(String termino, int page, int size);
    Persona findById(String ndocumento);
    Persona create(PersonaBodyDto personaBodyDto);
    Persona update(String ndocumento, PersonaBodyDto personaBodyDto);
    void delete(String id);
    Persona consultar(String ndocumento);
}
