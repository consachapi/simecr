package pe.regioncusco.gob.simecr.security.infraestructures.database.commons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PersonaEntity;

import java.util.stream.Collectors;

public final class PersonaCommonData {
    public static Page<Persona> toPagePersona(Page<PersonaEntity> personaEntityPage, Pageable pageable){
        long totalElements = personaEntityPage.getTotalElements();
        return new PageImpl<Persona>(personaEntityPage.stream().map(personaEntity -> personaEntity.toPersona()).collect(Collectors.toList()), pageable, totalElements);
    }
}
