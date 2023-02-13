package pe.regioncusco.gob.simecr.security.domain.commons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.OficinaEntity;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PersonaEntity;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;
import pe.regioncusco.gob.simecr.security.infraestructures.feign.models.PersonaData;

import java.util.List;
import java.util.stream.Collectors;

public final class PersonaCommon {
    public static List<Persona> listPersonaEntityToListPersona(List<PersonaEntity> personaEntities){
        return personaEntities.stream().map(PersonaEntity::toPersona).collect(Collectors.toList());
    }

    public static Page<Persona> toPagePersona(Page<PersonaEntity> personaEntityPage, Pageable pageable){
        long totalElements = personaEntityPage.getTotalElements();
        return new PageImpl<Persona>(personaEntityPage.stream().map(personaEntity -> personaEntity.toPersona()).collect(Collectors.toList()), pageable, totalElements);
    }

    public static PersonaEntity toPersonaEntity(Persona persona){
        OficinaEntity oficinaEntity = new OficinaEntity();
        oficinaEntity.setId(persona.getArea().getId());
        oficinaEntity.setAbreviatura(persona.getArea().getAbreviatura());
        oficinaEntity.setDescripcion(persona.getArea().getDescripcion());

        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setNdocumento(persona.getNdocumento());
        personaEntity.setNombres(persona.getNombres());
        personaEntity.setApellidos(persona.getApellidos());
        personaEntity.setArea(oficinaEntity);
        personaEntity.setCargo(CargoCommon.toCargoEntity(persona.getCargo()));
        personaEntity.setTelefono(persona.getTelefono());
        personaEntity.setEmail(persona.getEmail());
        personaEntity.setDireccion(persona.getDireccion());
        personaEntity.setPerfil(PerfilCommon.toPerfilEntity(persona.getPerfil()));
        personaEntity.setActive(persona.isActive());
        return personaEntity;
    }

    public static Persona toPersona(PersonaData personaData, Oficina oficina){
        Persona persona = new Persona();
        persona.setNdocumento(personaData.getNumeroDocumento());
        persona.setNombres(personaData.getNombres());
        persona.setApellidos(personaData.getApellidos());
        persona.setArea(oficina);
        persona.setTelefono(personaData.getTelefono());
        persona.setEmail(personaData.getEmail());
        persona.setDireccion(personaData.getDireccion());
        return persona;
    }

}
