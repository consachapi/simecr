package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities.RemediacionVerificacionEntity;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.RemediacionVerificacion;

public final class RemediacionVerificacionAdapterCommon {
    public static RemediacionVerificacionEntity toRemediacionVerificacionEntity(RemediacionVerificacion remediacionVerificacion){
        RemediacionVerificacionEntity remediacionVerificacionEntity = new RemediacionVerificacionEntity();
        BeanUtils.copyProperties(remediacionVerificacion, remediacionVerificacionEntity);
        return remediacionVerificacionEntity;
    }
}
