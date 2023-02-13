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
import pe.regioncusco.gob.simecr.application.parametros.domain.services.OficinaService;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(OficinaController.OFICINA)
public class OficinaController {
    public static final String OFICINA = "/v1/parametro/oficina";

    private static final String LISTAR = "/listar";
    private static final String SELECCIONAR = "/seleccionar";

    @Autowired private OficinaService oficinaService;

    @GetMapping(SELECCIONAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<MyValue>> findAll(){
        return new ResponseEntity<>(oficinaService.findAllSelection(), HttpStatus.OK);
    }

}
