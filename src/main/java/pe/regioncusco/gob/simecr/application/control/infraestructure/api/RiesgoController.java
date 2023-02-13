package pe.regioncusco.gob.simecr.modules.control.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.modules.control.domain.models.Riesgo;
import pe.regioncusco.gob.simecr.modules.control.domain.services.RiesgoService;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.RiesgoDto;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(RiesgoController.RIESGO)
public class RiesgoController {
    public static final String RIESGO = "/v1/riesgo";

    private static final String LISTAR = "/listar";
    private static final String LISTAR_PRODUCTO = "/buscar/producto/{producto}";
    private static final String CREAR = "/crear";
    private static final String EDITAR = "/editar/{id}";
    private static final String MOSTRAR = "/mostrar/{id}";
    private static final String ELIMINAR = "/eliminar/{id}";

    @Autowired private RiesgoService riesgoService;

    @GetMapping(LISTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<List<RiesgoDto>> findAll(){
        return new ResponseEntity<>(riesgoService.findAll(), HttpStatus.OK);
    }

    @GetMapping(LISTAR_PRODUCTO)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<List<MyValue>> findAllByProducto(@PathVariable Long producto){
        return new ResponseEntity<>(riesgoService.findAllByProducto(producto), HttpStatus.OK);
    }

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<Riesgo> save(@RequestBody Riesgo riesgo){
        return new ResponseEntity<>(riesgoService.save(riesgo), HttpStatus.CREATED);
    }

    @PutMapping(EDITAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<Riesgo> update(@PathVariable Long id, @RequestBody Riesgo riesgo){
        return new ResponseEntity<>(riesgoService.update(id, riesgo), HttpStatus.OK);
    }

    @DeleteMapping(ELIMINAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public void delete(@PathVariable Long id){
        riesgoService.delete(id);
    }

    @GetMapping(MOSTRAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<RiesgoDto> find(@PathVariable Long id){
        return new ResponseEntity<>(riesgoService.findByRiesgoDtoById(id), HttpStatus.OK);
    }

}
