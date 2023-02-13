package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.MedidasRemediacionDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.MedidasRemediacionService;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(MedidasRemediacionController.MEDIDAS_REMEDIACION)
public class MedidasRemediacionController {
    public static final String MEDIDAS_REMEDIACION = "/v1/medidas/remediacion";

    private static final String CREAR = "/crear";
    private static final String LISTAR = "/listar";
    private static final String EDITAR = "/editar/{id}";
    private static final String MOSTRAR = "/mostrar/{id}";
    private static final String ELIMINAR = "/eliminar/{id}";

    @Autowired private MedidasRemediacionService medidasRemediacionService;

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<MedidasRemediacionDto> save(@RequestBody MedidasRemediacion medidasRemediacion){
        return new ResponseEntity<>(medidasRemediacionService.save(medidasRemediacion), HttpStatus.CREATED);
    }

    @GetMapping(LISTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<MedidasRemediacionDto>> findAll(){
        return new ResponseEntity<>(medidasRemediacionService.findAll(), HttpStatus.OK);
    }

    @PutMapping(EDITAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<MedidasRemediacionDto> update(@PathVariable Long id, @RequestBody MedidasRemediacion medidasRemediacion){
        return new ResponseEntity<>(medidasRemediacionService.update(id, medidasRemediacion), HttpStatus.OK);
    }

    @GetMapping(MOSTRAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<MedidasRemediacionDto> find(@PathVariable Long id){
        return new ResponseEntity<>(medidasRemediacionService.find(id), HttpStatus.OK);
    }

}
