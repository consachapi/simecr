package pe.regioncusco.gob.simecr.modules.reportes.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import pe.regioncusco.gob.simecr.commons.ParamsManager;
import pe.regioncusco.gob.simecr.config.Rest;
import pe.regioncusco.gob.simecr.modules.reportes.domain.models.RiesgoDeficienciaTotal;
import pe.regioncusco.gob.simecr.modules.reportes.domain.models.MedidasDashboard;
import pe.regioncusco.gob.simecr.modules.reportes.domain.services.DashboardService;

import javax.annotation.security.RolesAllowed;

@Rest
@RequestMapping(DashboardController.DASHBOARD)
public class DashboardController {
    public static final String DASHBOARD = "/v1/dashboard";

    private static final String MEDIDAS_CONTROL = "/medidas/control";
    private static final String MEDIDAS_REMEDIACION = "/medidas/remediacion";
    private static final String RIESGO_DEFICIENCIA = "/riesgo/deficiencia/total";

    @Autowired private DashboardService dashboardService;

    @GetMapping(MEDIDAS_CONTROL)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<MedidasDashboard> findAllMedidasControl(){
        return new ResponseEntity<>(dashboardService.findAllMedidasControl(), HttpStatus.OK);
    }

    @GetMapping(MEDIDAS_REMEDIACION)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<MedidasDashboard> findAllMedidasRemediacion(){
        return new ResponseEntity<>(dashboardService.findAllMedidasRemediacion(), HttpStatus.OK);
    }

    @GetMapping(RIESGO_DEFICIENCIA)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<RiesgoDeficienciaTotal> findAllTotalRiesgoDeficiencia(){
        return new ResponseEntity<>(dashboardService.findAllTotalRiesgoDeficiencia(), HttpStatus.OK);
    }
}
