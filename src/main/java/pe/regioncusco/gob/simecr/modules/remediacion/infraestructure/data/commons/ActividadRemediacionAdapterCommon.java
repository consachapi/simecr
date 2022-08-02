package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.ActividadRemediacionEntity;

public final class ActividadRemediacionAdapterCommon {
    public static ActividadRemediacionEntity toActividadRemediacionEntity(ActividadRemediacion actividadRemediacion){
        ActividadRemediacionEntity actividadRemediacionEntity = new ActividadRemediacionEntity();
        BeanUtils.copyProperties(actividadRemediacion, actividadRemediacionEntity);
        return actividadRemediacionEntity;
    }
}
