package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.RemediacionVerificacion;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.RemediacionVerificacionEntity;

public final class RemediacionVerificacionAdapterCommon {
    public static RemediacionVerificacionEntity toRemediacionVerificacionEntity(RemediacionVerificacion remediacionVerificacion){
        RemediacionVerificacionEntity remediacionVerificacionEntity = new RemediacionVerificacionEntity();
        BeanUtils.copyProperties(remediacionVerificacion, remediacionVerificacionEntity);
        return remediacionVerificacionEntity;
    }
}
