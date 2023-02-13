package pe.regioncusco.gob.simecr.security.infraestructures.apis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.commons.ParamsManager;
import pe.regioncusco.gob.simecr.config.Rest;
import pe.regioncusco.gob.simecr.security.domain.services.CargoService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(CargoController.CARGO)
public class CargoController {
    public static final String CARGO ="/v1/cargo";
    private static final String SELECCIONAR = "/seleccionar";

    @Autowired private CargoService cargoService;

    @GetMapping(SELECCIONAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<List<MyValue>> findAllSelection(){
        return new ResponseEntity<>(cargoService.findAllSelection(), HttpStatus.OK);
    }

}
