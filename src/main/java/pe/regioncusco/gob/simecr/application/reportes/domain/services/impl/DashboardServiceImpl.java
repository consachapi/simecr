package pe.regioncusco.gob.simecr.application.reportes.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.application.control.domain.enums.ControlVerificacionEstado;
import pe.regioncusco.gob.simecr.application.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.RiesgoDto;
import pe.regioncusco.gob.simecr.application.control.domain.services.ControlVerificacionService;
import pe.regioncusco.gob.simecr.application.control.domain.services.RiesgoService;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.RemediacionVerificacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.DeficienciaDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.DeficienciaService;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.RemediacionVerificacionService;
import pe.regioncusco.gob.simecr.application.reportes.domain.services.DashboardService;
import pe.regioncusco.gob.simecr.application.reportes.domain.models.RiesgoDeficienciaTotal;
import pe.regioncusco.gob.simecr.application.reportes.domain.models.MedidasDashboard;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    private static final Logger LOG = LoggerFactory.getLogger(DashboardServiceImpl.class);

    @Autowired private ControlVerificacionService controlVerificacionService;
    @Autowired private RemediacionVerificacionService remediacionVerificacionService;
    @Autowired private RiesgoService riesgoService;
    @Autowired private DeficienciaService deficienciaService;

    @Override
    public MedidasDashboard findAllMedidasControl() {
        List<ControlVerificacion> controlVerificacions = controlVerificacionService.findAllControlVerificacion();
        MedidasDashboard medidasDashboard = new MedidasDashboard();
        medidasDashboard.setMedida("Medidas de Control");
        medidasDashboard.setTotal(controlVerificacions.size());

        Integer ejecutados = 0;
        for(ControlVerificacion controlVerificacion: controlVerificacions){
            if(controlVerificacion.getEstado() == ControlVerificacionEstado.COMPLETADO.value()){
                ejecutados++;
            }
        }
        medidasDashboard.setEjecutado(ejecutados);
        medidasDashboard.setPendiente(controlVerificacions.size() - ejecutados);
        return medidasDashboard;
    }

    @Override
    public MedidasDashboard findAllMedidasRemediacion() {
        List<RemediacionVerificacion> remediacionVerificacions = remediacionVerificacionService.findAllRemediacionVerificacion();
        MedidasDashboard medidasDashboard = new MedidasDashboard();
        medidasDashboard.setMedida("Medidas de Remediación");
        medidasDashboard.setTotal(remediacionVerificacions.size());

        Integer ejecutados = 0;
        for(RemediacionVerificacion remediacionVerificacion: remediacionVerificacions){
            if(remediacionVerificacion.getEstado() == ControlVerificacionEstado.COMPLETADO.value()){
                ejecutados++;
            }
        }
        medidasDashboard.setEjecutado(ejecutados);
        medidasDashboard.setPendiente(remediacionVerificacions.size() - ejecutados);
        return medidasDashboard;
    }

    @Override
    public RiesgoDeficienciaTotal findAllTotalRiesgoDeficiencia() {
        List<RiesgoDto> riesgos = riesgoService.findAll();
        List<DeficienciaDto> deficiencias = deficienciaService.findAll();
        RiesgoDeficienciaTotal riesgoDeficienciaTotal = new RiesgoDeficienciaTotal();
        riesgoDeficienciaTotal.setRiesgo(riesgos.size());
        riesgoDeficienciaTotal.setDeficiencia(deficiencias.size());
        return riesgoDeficienciaTotal;
    }

}
