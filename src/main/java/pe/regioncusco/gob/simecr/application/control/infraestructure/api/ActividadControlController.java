package pe.regioncusco.gob.simecr.modules.control.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ActividadControlBodyDto;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ActividadControlDto;
import pe.regioncusco.gob.simecr.modules.control.domain.services.ActividadControlService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(ActividadControlController.ACTIVIDAD_CONTROL)
public class ActividadControlController {
    public static final String ACTIVIDAD_CONTROL = "/v1/actividad/control";

    private static final String CREAR = "/crear/{id}";
    private static final String LISTAR_CONTROL_VERIFICACION = "/listar/cverificacion/{id}";
    private static final String EDITAR = "/editar/{id}";
    private static final String MOSTRAR = "/mostrar/{id}";
    private static final String ELIMINAR = "/eliminar/{id}";
    private static final String FINALIZAR = "/finalizar/{id}";

    @Autowired private ActividadControlService actividadControlService;

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<ActividadControlDto> save(@PathVariable Long id, @RequestBody ActividadControlBodyDto actividad){
        return new ResponseEntity<>(actividadControlService.save(id, actividad), HttpStatus.CREATED);
    }

    @GetMapping(LISTAR_CONTROL_VERIFICACION)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<List<ActividadControlDto>> findAll(@PathVariable Long id){
        return new ResponseEntity<>(actividadControlService.findAllByControlVerificacion(id), HttpStatus.OK);
    }

    @DeleteMapping(ELIMINAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public void delete(@PathVariable Long id){
        actividadControlService.delete(id);
    }

    @PutMapping(FINALIZAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<ActividadControlDto> finalize(@PathVariable Long id){
        return new ResponseEntity<>(actividadControlService.finalize(id), HttpStatus.OK);
    }

}
