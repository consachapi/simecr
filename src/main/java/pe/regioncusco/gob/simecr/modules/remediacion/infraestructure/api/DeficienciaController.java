package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.commons.ParamsManager;
import pe.regioncusco.gob.simecr.config.Rest;
import pe.regioncusco.gob.simecr.modules.control.domain.models.Riesgo;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.RiesgoDto;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.DeficienciaBodyDto;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.DeficienciaDto;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.services.DeficienciaService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(DeficienciaController.DEFICIENCIA)
public class DeficienciaController {
    public static final String DEFICIENCIA = "/v1/deficiencia";

    private static final String LISTAR = "/listar";
    private static final String CREAR = "/crear";
    private static final String EDITAR = "/editar/{id}";
    private static final String MOSTRAR = "/mostrar/{id}";
    private static final String ELIMINAR = "/eliminar/{id}";
    private static final String SELECCIONAR = "/seleccionar";

    @Autowired private DeficienciaService deficienciaService;

    @GetMapping(LISTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<List<DeficienciaDto>> findAll(){
        return new ResponseEntity<>(deficienciaService.findAll(), HttpStatus.OK);
    }

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<DeficienciaDto> save(@RequestBody DeficienciaBodyDto deficienciaBodyDto){
        return new ResponseEntity<>(deficienciaService.save(deficienciaBodyDto), HttpStatus.CREATED);
    }

    @PutMapping(EDITAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<DeficienciaDto> update(@PathVariable Long id, @RequestBody DeficienciaBodyDto deficienciaBodyDto){
        return new ResponseEntity<>(deficienciaService.update(id, deficienciaBodyDto), HttpStatus.OK);
    }

    @GetMapping(MOSTRAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<DeficienciaDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(deficienciaService.show(id), HttpStatus.OK);
    }

    @DeleteMapping(ELIMINAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public void delete(@PathVariable Long id){
        deficienciaService.delete(id);
    }

    @GetMapping(SELECCIONAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<List<MyValue>> selection(){
        return new ResponseEntity<>(deficienciaService.selection(), HttpStatus.OK);
    }

}
