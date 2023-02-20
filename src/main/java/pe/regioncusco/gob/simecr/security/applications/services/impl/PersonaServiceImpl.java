package pe.regioncusco.gob.simecr.security.applications.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.core.enums.Status;
import pe.regioncusco.gob.simecr.security.applications.persistences.PersonaPersistence;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.core.exceptions.ConflictException;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.application.parametros.domain.services.OficinaService;
import pe.regioncusco.gob.simecr.security.domains.dtos.PersonaBodyDto;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PersonaEntity;
import pe.regioncusco.gob.simecr.security.infraestructures.database.respositorys.PersonaEntityRepository;
import pe.regioncusco.gob.simecr.security.domains.commons.PersonaCommon;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;
import pe.regioncusco.gob.simecr.security.applications.services.PersonaService;
import pe.regioncusco.gob.simecr.security.applications.services.UsuarioService;
import pe.regioncusco.gob.simecr.security.infraestructures.feign.PersonaFeignClient;
import pe.regioncusco.gob.simecr.security.infraestructures.feign.models.PersonaData;
import pe.regioncusco.gob.simecr.security.infraestructures.feign.models.PersonaTpl;

import java.util.Date;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {
    private static final Logger LOG = LoggerFactory.getLogger(PersonaServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private PersonaPersistence personaPersistence;
    @Autowired private UsuarioService usuarioService;
    @Autowired private PersonaFeignClient personaFeignClient;
    @Autowired private OficinaService oficinaService;

    @Override
    public Page<Persona> findAll(int page, int size) {
        return personaPersistence.findAll(page, size);
    }

    @Override
    public Page<Persona> findByNumeroDocumentoContains(String termino, int page, int size) {
        return personaPersistence.findByNumeroDocumentoContains(termino, page, size);
    }

    @Override
    public Persona findPersonaById(String ndocumento) {
        return findById(ndocumento);
    }


    /*
    @Override
    public Persona create(PersonaBodyDto personaBodyDto) {
        Persona persona = consultar(personaBodyDto.getNdocumento());
        persona.setCargo(personaBodyDto.getCargo());
        persona.setPerfil(personaBodyDto.getPerfil());
        persona.setActive(true);
        Optional<PersonaEntity> personaEntity = personaEntityRepository.findById(persona.getNdocumento());
        if(personaEntity.isPresent()){
            throw new ConflictException("Persona con DNI " + persona.getNdocumento() + ", ya existe");
        }

        PersonaEntity nuevo = PersonaCommon.toPersonaEntity(persona);
        nuevo.setFechaRegistro(new Date());
        nuevo.setUsuario(accessToken.getUserId());
        PersonaEntity nuevoPersonaEntity = personaEntityRepository.save(nuevo);
        usuarioService.setEnabledAndPerfil(nuevoPersonaEntity.getNdocumento(), true, nuevoPersonaEntity.getPerfil().getDescripcion());
        return nuevoPersonaEntity.toPersona();
    }

    @Override
    public Persona update(String ndocumento, PersonaBodyDto personaBodyDto) {
        PersonaEntity personaEntity = findByNumDoc(ndocumento);
        Persona persona = personaEntity.toPersona();
        persona.setCargo(personaBodyDto.getCargo());
        persona.setPerfil(personaBodyDto.getPerfil());
        persona.setActive(personaBodyDto.getEstado());

        PersonaEntity personActual = PersonaCommon.toPersonaEntity(persona);
        BeanUtils.copyProperties(personActual, personaEntity);
        personaEntity.setFechaRegistro(new Date());
        personaEntity.setUsuario(accessToken.getUserId());
        PersonaEntity updatePersonaEntity = personaEntityRepository.save(personaEntity);
        LOG.info("Persona con ndoc {}, actualizado", updatePersonaEntity.getNdocumento());
        usuarioService.changePerfil(personaEntity.getNdocumento(), personaEntity.getPerfil().getDescripcion());
        return updatePersonaEntity.toPersona();
    }

    @Override
    public void delete(String id) {
        PersonaEntity personaEntity = findByNumDoc(id);
        personaEntity.setActive(Status.DISABLED.value());
        personaEntity.setFechaRegistro(new Date());
        personaEntity.setUsuario(accessToken.getUserId());
        personaEntityRepository.save(personaEntity);
        LOG.info("Personal con ndoc {}, eliminado", id);
        usuarioService.removePerfil(personaEntity.getNdocumento(), personaEntity.getPerfil().getDescripcion());
    }

    @Override
    public Persona consultar(String ndocumento) {
        PersonaTpl personaTpl = personaFeignClient.getPersona(ndocumento);
        if(!personaTpl.isSuccess()){
            throw new NotFoundException("No existe persona con documento número " + ndocumento);
        }
        PersonaData personaData = personaTpl.getData().get(0);
        String abrevOficina = personaData.getOficinaAbreviatura();
        Oficina oficina = oficinaService.findByAbreviatura(abrevOficina);
        return PersonaCommon.toPersona(personaData, oficina);
    }
*/
    private Persona findById(String id){
        Optional<Persona> optional = personaPersistence.findByNdocumento(id);
        if(!optional.isPresent()){
            LOG.error("No existe la persona con documento {}", id);
            throw new NotFoundException("No existe la persona con documento " + id);
        }
        return optional.get();
    }

}
