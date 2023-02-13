package pe.regioncusco.gob.simecr.security.infraestructures.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;
import pe.regioncusco.gob.simecr.security.domains.dtos.PersonaBodyDto;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;
import pe.regioncusco.gob.simecr.security.applications.services.PersonaService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Rest
@RequestMapping(PersonalController.PERSONAL)
public class PersonalController {
    public static final String PERSONAL = "/v1/persona";

    private static final String LISTAR = "/listar";
    private static final String BUSCAR = "/buscar/{termino}";
    private static final String CONSULTAR = "/consultar/{ndocumento}";
    private static final String MOSTRAR = "/mostrar/{ndocumento}";
    private static final String CREAR = "/crear";
    private static final String EDITAR = "/editar/{ndocumento}";
    private static final String ELIMINAR = "/eliminar/{ndocumento}";

    @Autowired private PersonaService personaService;

    @GetMapping(LISTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public Page<Persona> findAll(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size") int size){
        return personaService.findAll(page,size);
    }

    @GetMapping(BUSCAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public Page<Persona> findByNumeroDocumentoContains(@PathVariable String termino, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size") int size){
        return personaService.findByNumeroDocumentoContains(termino, page, size);
    }

    @GetMapping(MOSTRAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<Persona> findById(@PathVariable String ndocumento){
        return new ResponseEntity<>(personaService.findById(ndocumento), HttpStatus.OK);
    }

    @GetMapping(CONSULTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<Persona> consultar(@PathVariable String ndocumento){
        return new ResponseEntity<>(personaService.consultar(ndocumento), HttpStatus.OK);
    }

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<Persona> create(@Valid @RequestBody PersonaBodyDto personaBodyDto){
        return new ResponseEntity<>(personaService.create(personaBodyDto), HttpStatus.CREATED);
    }

    @PutMapping(EDITAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<Persona> update(@PathVariable String ndocumento, @Valid @RequestBody PersonaBodyDto personaBodyDto){
        return new ResponseEntity<>(personaService.update(ndocumento, personaBodyDto), HttpStatus.OK);
    }

    @DeleteMapping(ELIMINAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public void delete(@PathVariable String ndocumento){
        personaService.delete(ndocumento);
    }

}
