package pe.regioncusco.gob.simecr.modules.parametros.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.commons.ParamsManager;
import pe.regioncusco.gob.simecr.config.Rest;
import pe.regioncusco.gob.simecr.modules.parametros.domain.services.ComponenteService;

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
    @RolesAllowed(ParamsManager.ROLE_ADMIN)
    public ResponseEntity<List<MyValue>> findAllSelectionByEje(@PathVariable Long id){
        return new ResponseEntity<>(componenteService.findAllSelectionByEje(id), HttpStatus.OK);
    }

}
