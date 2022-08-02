package pe.regioncusco.gob.simecr.modules.control.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.commons.ParamsManager;
import pe.regioncusco.gob.simecr.config.Rest;
import pe.regioncusco.gob.simecr.modules.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.MedidasControlDto;
import pe.regioncusco.gob.simecr.modules.control.domain.services.MedidasControlService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(MedidasControlController.MEDIDAS_CONTROL)
public class MedidasControlController {
    public static final String MEDIDAS_CONTROL = "/v1/medidas/control";

    private static final String CREAR = "/crear";
    private static final String LISTAR = "/listar";
    private static final String EDITAR = "/editar/{id}";
    private static final String MOSTRAR = "/mostrar/{id}";
    private static final String ELIMINAR = "/eliminar/{id}";

    @Autowired private MedidasControlService medidasControlService;

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<MedidasControlDto> save(@RequestBody MedidasControl medidasControl){
        return new ResponseEntity<>(medidasControlService.save(medidasControl), HttpStatus.CREATED);
    }

    @PutMapping(EDITAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<MedidasControlDto> update(@PathVariable Long id, @RequestBody MedidasControl medidasControl){
        return new ResponseEntity<>(medidasControlService.update(id, medidasControl), HttpStatus.OK);
    }

    @GetMapping(LISTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<List<MedidasControlDto>> findAll(){
        return new ResponseEntity<>(medidasControlService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(ELIMINAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public void delete(@PathVariable Long id){
        medidasControlService.delete(id);
    }

    @GetMapping(MOSTRAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<MedidasControlDto> findAll(@PathVariable Long id){
        return new ResponseEntity<>(medidasControlService.findMedidaControlById(id), HttpStatus.OK);
    }

}
