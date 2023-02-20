package pe.regioncusco.gob.simecr.security.infraestructures.database.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.security.applications.persistences.PersonaPersistence;
import pe.regioncusco.gob.simecr.security.domains.commons.PersonaCommon;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;
import pe.regioncusco.gob.simecr.security.infraestructures.database.commons.PersonaCommonData;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PersonaEntity;
import pe.regioncusco.gob.simecr.security.infraestructures.database.respositorys.PersonaEntityRepository;

import java.util.Optional;

@Repository
public class PersonaPersistenceData implements PersonaPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(PersonaPersistenceData.class);

    @Autowired private PersonaEntityRepository personaEntityRepository;

    @Override
    public Page<Persona> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombres").and(Sort.by("ndocumento")));
        Page<PersonaEntity> personaEntities = personaEntityRepository.findAll(pageable);
        LOG.info("Personas obtenidad {}", personaEntities.getTotalElements());
        return PersonaCommonData.toPagePersona(personaEntities, pageable);
    }

    @Override
    public Page<Persona> findByNumeroDocumentoContains(String termino, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombres").and(Sort.by("ndocumento")));
        Page<PersonaEntity> personaEntities = personaEntityRepository.findAllByNdocumentoContains(termino, pageable);
        LOG.info("Buscando el personal {}, encontrados {}", termino, personaEntities.getTotalElements());
        return PersonaCommonData.toPagePersona(personaEntities, pageable);
    }

    @Override
    public Optional<Persona> findByNdocumento(String numero) {
        Optional<PersonaEntity> personaEntity = personaEntityRepository.findById(numero);
        return personaEntity.map(PersonaEntity::toPersona);
    }

}
