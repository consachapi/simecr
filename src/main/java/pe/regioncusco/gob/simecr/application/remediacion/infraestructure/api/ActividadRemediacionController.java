package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.ActividadRemediacionBodyDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.ActividadRemediacionDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.ActividadRemediacionService;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(ActividadRemediacionController.ACTIVIDAD_REMEDIACION)
public class ActividadRemediacionController {
    public static final String ACTIVIDAD_REMEDIACION = "/v1/actividad/remediacion";

    private static final String CREAR = "/crear/{id}";
    private static final String LISTAR_REMEDIACION_VERIFICACION = "/listar/rverificacion/{id}";
    private static final String EDITAR = "/editar/{id}";
    private static final String MOSTRAR = "/mostrar/{id}";
    private static final String ELIMINAR = "/eliminar/{id}";
    private static final String FINALIZAR = "/finalizar/{id}";

    @Autowired
    private ActividadRemediacionService actividadRemediacionService;

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<ActividadRemediacionDto> save(@PathVariable Long id, @RequestBody ActividadRemediacionBodyDto actividad){
        return new ResponseEntity<>(actividadRemediacionService.save(id, actividad), HttpStatus.CREATED);
    }

    @GetMapping(LISTAR_REMEDIACION_VERIFICACION)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_USER)
    public ResponseEntity<List<ActividadRemediacionDto>> findAll(@PathVariable Long id){
        return new ResponseEntity<>(actividadRemediacionService.findAllByRemediacionVerificacion(id), HttpStatus.OK);
    }

    @DeleteMapping(ELIMINAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public void delete(@PathVariable Long id){
        actividadRemediacionService.delete(id);
    }

    @PutMapping(FINALIZAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<ActividadRemediacionDto> finalize(@PathVariable Long id){
        return new ResponseEntity<>(actividadRemediacionService.finalize(id), HttpStatus.OK);
    }

}
