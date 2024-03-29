package pe.regioncusco.gob.simecr.application.control.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.ControlVerificacionBodyDto;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.ControlVerificacionDto;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.application.control.domain.services.ControlVerificacionService;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(ControlVerificacionController.CONTROL_VERIFICACION)
public class ControlVerificacionController {
    public static final String CONTROL_VERIFICACION = "/v1/control/verificacion";

    private static final String LISTAR = "/listar";
    private static final String LISTAR_MCONTROL = "/buscar/mcontrol/{id}";
    private static final String CREAR = "/crear";
    private static final String ASIGNAR_RESPONSABLE = "/asignar/responsable/{id}";
    private static final String EDITAR = "/editar/{id}";
    private static final String MOSTRAR = "/mostrar/{id}";
    private static final String ELIMINAR = "/eliminar/{id}";
    private static final String LISTAR_POR_USUARIO = "/listar/usuario";
    private static final String CAMBIAR_ESTADO = "/cambiar/{id}";

    @Autowired private ControlVerificacionService controlVerificacionService;

    @GetMapping(LISTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<ControlVerificacionDto>> findAll(){
        return new ResponseEntity<>(controlVerificacionService.findAll(), HttpStatus.OK);
    }

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<ControlVerificacionDto> save(@RequestBody ControlVerificacionBodyDto controlVerificacionBodyDto){
        return new ResponseEntity<>(controlVerificacionService.save(controlVerificacionBodyDto), HttpStatus.CREATED);
    }

    @GetMapping(LISTAR_MCONTROL)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<ControlVerificacionDto>> findAllByMedidaControl(@PathVariable Long id){
        return new ResponseEntity<>(controlVerificacionService.findAllByMedidaControl(id), HttpStatus.OK);
    }

    @PostMapping(ASIGNAR_RESPONSABLE)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<Oficina>> set(@PathVariable Long id, @RequestBody List<Oficina> oficina){
        return new ResponseEntity<>(controlVerificacionService.setResponsableOficina(id, oficina), HttpStatus.CREATED);
    }

    @GetMapping(LISTAR_POR_USUARIO)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_USER)
    public ResponseEntity<List<ControlVerificacionDto>> findAllByUsuario(){
        return new ResponseEntity<>(controlVerificacionService.findAllByUsuario(), HttpStatus.OK);
    }

    @GetMapping(MOSTRAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_USER)
    public ResponseEntity<ControlVerificacionDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(controlVerificacionService.find(id), HttpStatus.OK);
    }

    @PutMapping(CAMBIAR_ESTADO)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<ControlVerificacionDto> cambiarEstado(@PathVariable Long id) {
        return new ResponseEntity(this.controlVerificacionService.cambiarEstado(id), HttpStatus.OK);
    }

}
