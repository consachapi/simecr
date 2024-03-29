package pe.regioncusco.gob.simecr.application.parametros.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.application.parametros.domain.services.ComponenteService;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(ComponenteController.COMPONENTE)
public class ComponenteController {
    public static final String COMPONENTE = "/v1/parametro/componente";

    private static final String SELECCIONAR = "/seleccionar";
    private static final String SELECCIONAR_EJE = "/seleccionar/eje/{id}";

    @Autowired private ComponenteService componenteService;

    @GetMapping(SELECCIONAR_EJE)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<List<MyValue>> findAllSelectionByEje(@PathVariable Long id){
        return new ResponseEntity<>(componenteService.findAllSelectionByEje(id), HttpStatus.OK);
    }

}
