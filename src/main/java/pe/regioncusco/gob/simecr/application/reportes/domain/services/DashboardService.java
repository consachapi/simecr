package pe.regioncusco.gob.simecr.application.reportes.domain.services;

import pe.regioncusco.gob.simecr.application.reportes.domain.models.RiesgoDeficienciaTotal;
import pe.regioncusco.gob.simecr.application.reportes.domain.models.MedidasDashboard;

public interface DashboardService {
    MedidasDashboard findAllMedidasControl();
    MedidasDashboard findAllMedidasRemediacion();
    RiesgoDeficienciaTotal findAllTotalRiesgoDeficiencia();
}
