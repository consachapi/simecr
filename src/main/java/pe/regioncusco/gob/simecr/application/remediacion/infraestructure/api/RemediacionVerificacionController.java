package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.RemediacionVerificacionBodyDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.RemediacionVerificacionDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.RemediacionVerificacionService;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(RemediacionVerificacionController.REMEDIACION_VERIFICACION)
public class RemediacionVerificacionController {
    public static final String REMEDIACION_VERIFICACION = "/v1/remediacion/verificacion";

    private static final String LISTAR = "/listar";
    private static final String LISTAR_MCONTROL = "/buscar/mremediacion/{id}";
    private static final String CREAR = "/crear";
    private static final String ASIGNAR_RESPONSABLE = "/asignar/responsable/{id}";
    private static final String MOSTRAR = "/mostrar/{id}";
    private static final String LISTAR_POR_USUARIO = "/listar/usuario";
    private static final String CAMBIAR_ESTADO = "/cambiar/{id}";

    @Autowired private RemediacionVerificacionService remediacionVerificacionService;

    @GetMapping(LISTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<RemediacionVerificacionDto>> findAll(){
        return new ResponseEntity<>(remediacionVerificacionService.findAll(), HttpStatus.OK);
    }

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<RemediacionVerificacionDto> save(@RequestBody RemediacionVerificacionBodyDto remediacionVerificacionBodyDto){
        return new ResponseEntity<>(remediacionVerificacionService.save(remediacionVerificacionBodyDto), HttpStatus.CREATED);
    }

    @GetMapping(LISTAR_MCONTROL)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<RemediacionVerificacionDto>> findAllByMedidaRemediacion(@PathVariable Long id){
        return new ResponseEntity<>(remediacionVerificacionService.findAllByMedidaRemediacion(id), HttpStatus.OK);
    }

    @PostMapping(ASIGNAR_RESPONSABLE)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<Oficina>> set(@PathVariable Long id, @RequestBody List<Oficina> oficina){
        return new ResponseEntity<>(remediacionVerificacionService.setResponsableOficina(id, oficina), HttpStatus.CREATED);
    }

    @GetMapping(MOSTRAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_USER)
    public ResponseEntity<RemediacionVerificacionDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(remediacionVerificacionService.find(id), HttpStatus.OK);
    }

    @GetMapping(LISTAR_POR_USUARIO)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_USER)
    public ResponseEntity<List<RemediacionVerificacionDto>> findAllByUsuario(){
        return new ResponseEntity<>(remediacionVerificacionService.findAllByUsuario(), HttpStatus.OK);
    }

    @PutMapping(CAMBIAR_ESTADO)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<RemediacionVerificacionDto> cambiarEstado(@PathVariable Long id) {
        return new ResponseEntity(this.remediacionVerificacionService.cambiarEstado(id), HttpStatus.OK);
    }

}
