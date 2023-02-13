package pe.regioncusco.gob.simecr.security.infraestructures.database.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.security.applications.persistences.PersonaPersistence;
import pe.regioncusco.gob.simecr.security.infraestructures.database.respositorys.PersonaEntityRepository;

@Repository
public class PersonaPersistenceData implements PersonaPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(PersonaPersistenceData.class);

    @Autowired private PersonaEntityRepository personaEntityRepository;



}
