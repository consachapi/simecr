package pe.regioncusco.gob.simecr.modules.reportes.domain.services;

import pe.regioncusco.gob.simecr.modules.reportes.domain.models.RiesgoDeficienciaTotal;
import pe.regioncusco.gob.simecr.modules.reportes.domain.models.MedidasDashboard;

public interface DashboardService {
    MedidasDashboard findAllMedidasControl();
    MedidasDashboard findAllMedidasRemediacion();
    RiesgoDeficienciaTotal findAllTotalRiesgoDeficiencia();
}
