package pe.regioncusco.gob.simecr.application.control.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.ProductoBodyDto;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.ProductoDto;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.application.control.domain.services.ProductoService;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(ProductoController.PRODUCTO)
public class ProductoController {
    public static final String PRODUCTO = "/v1/producto";

    private static final String LISTAR = "/listar";
    private static final String CREAR = "/crear";
    private static final String EDITAR = "/editar/{id}";
    private static final String MOSTRAR = "/mostrar/{id}";
    private static final String ELIMINAR = "/eliminar/{id}";
    private static final String SELECCIONAR = "/seleccionar";

    @Autowired private ProductoService productoService;

    @GetMapping(LISTAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<ProductoDto>> findAll(){
        return new ResponseEntity<>(productoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<ProductoDto> save(@RequestBody ProductoBodyDto producto){
        return new ResponseEntity<>(productoService.save(producto), HttpStatus.CREATED);
    }

    @PutMapping(EDITAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<ProductoDto> update(@PathVariable Long id, @RequestBody ProductoBodyDto producto){
        return new ResponseEntity<>(productoService.update(id, producto), HttpStatus.OK);
    }

    @GetMapping(MOSTRAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_USER)
    public ResponseEntity<ProductoDto> find(@PathVariable Long id){
        return new ResponseEntity<>(productoService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(ELIMINAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public void delete(@PathVariable Long id){
        productoService.delete(id);
    }

    @GetMapping(SELECCIONAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_USER)
    public ResponseEntity<List<MyValue>> findAllActive(){
        return new ResponseEntity<>(productoService.findAllActive(), HttpStatus.OK);
    }

}
