package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities.ActividadRemediacionEntity;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.ActividadRemediacion;

public final class ActividadRemediacionAdapterCommon {
    public static ActividadRemediacionEntity toActividadRemediacionEntity(ActividadRemediacion actividadRemediacion){
        ActividadRemediacionEntity actividadRemediacionEntity = new ActividadRemediacionEntity();
        BeanUtils.copyProperties(actividadRemediacion, actividadRemediacionEntity);
        return actividadRemediacionEntity;
    }
}
