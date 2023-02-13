package pe.regioncusco.gob.simecr.security.infraestructures.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.security.applications.services.CargoService;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

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
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<MyValue>> findAllSelection(){
        return new ResponseEntity<>(cargoService.findAllSelection(), HttpStatus.OK);
    }

}
