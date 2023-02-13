package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ControlVerificacionEntity;

public final class ControlVerificacionAdapterCommon {
    public static ControlVerificacionEntity toControlVerificacionEntity(ControlVerificacion controlVerificacion){
        ControlVerificacionEntity controlVerificacionEntity = new ControlVerificacionEntity();
        BeanUtils.copyProperties(controlVerificacion, controlVerificacionEntity);
        return controlVerificacionEntity;
    }
}
