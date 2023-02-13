package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ActividadControlEntity;

public class ActividadControlAdapterCommon {
    public static ActividadControlEntity toActividadControlEntity(ActividadControl actividadControl){
        ActividadControlEntity controlEntity = new ActividadControlEntity();
        BeanUtils.copyProperties(actividadControl, controlEntity);
        return controlEntity;
    }
}
