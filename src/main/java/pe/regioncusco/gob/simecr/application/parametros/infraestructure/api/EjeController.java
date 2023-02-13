package pe.regioncusco.gob.simecr.application.parametros.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.application.parametros.domain.services.EjeService;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(EjeController.EJE)
public class EjeController {
    public static final String EJE = "/v1/parametro/eje";

    private static final String SELECCIONAR = "/seleccionar";

    @Autowired private EjeService ejeService;

    @GetMapping(SELECCIONAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<MyValue>> findAllSelection(){
        return new ResponseEntity<>(ejeService.findAllSelection(), HttpStatus.OK);
    }
}
