package pe.regioncusco.gob.simecr.application.configuracion.infraestructure.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.application.configuracion.application.services.UnidadEjecutoraService;
import pe.regioncusco.gob.simecr.application.configuracion.domain.models.UnidadEjecutoria;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(UnidadEjecutoriaController.UNIDAD_EJECUTORA)
public class UnidadEjecutoriaController {
    public static final String UNIDAD_EJECUTORA = "/v1/uejecutora";
    private static final String LISTAR = "/listar";
    private static final String CREAR = "/crear";
    private static final String EDITAR = "/editar/{id}";
    private static final String BUSCAR = "/buscar/{id}";
    private static final String HABILITAR = "/habilitar/{id}";
    private static final String BLOQUEAR = "/bloquear/{id}";

    @Autowired private UnidadEjecutoraService unidadEjecutoraService;

    @GetMapping(LISTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_SUPER)
    public ResponseEntity<List<UnidadEjecutoria>> findAll(){
        return new ResponseEntity<>(unidadEjecutoraService.findAll(), HttpStatus.OK);
    }

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsSecurity.ROLE_SUPER)
    public ResponseEntity<UnidadEjecutoria> create(@RequestBody UnidadEjecutoria unidadEjecutoria){
        return new ResponseEntity<>(unidadEjecutoraService.create(unidadEjecutoria), HttpStatus.CREATED);
    }

    @PutMapping(EDITAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_SUPER)
    public ResponseEntity<UnidadEjecutoria> update(@PathVariable String id, @RequestBody UnidadEjecutoria unidadEjecutoria){
        return new ResponseEntity<>(unidadEjecutoraService.update(id, unidadEjecutoria), HttpStatus.OK);
    }

    @GetMapping(BUSCAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_SUPER)
    public ResponseEntity<UnidadEjecutoria> findById(@PathVariable String id){
        return new ResponseEntity<>(unidadEjecutoraService.findUnidadEjecutoraById(id), HttpStatus.OK);
    }

    @DeleteMapping(BLOQUEAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsSecurity.ROLE_SUPER)
    public void disabled(@PathVariable String id){
        unidadEjecutoraService.disabled(id);
    }

    @DeleteMapping(HABILITAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsSecurity.ROLE_SUPER)
    public void enabled(@PathVariable String id){
        unidadEjecutoraService.enabled(id);
    }

}
