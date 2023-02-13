package pe.regioncusco.gob.simecr.application.control.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.control.domain.models.Riesgo;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.RiesgoEntity;

public final class RiesgoAdapterCommon {
    public static RiesgoEntity toRiesgoEntity(Riesgo riesgo){
        RiesgoEntity riesgoEntity = new RiesgoEntity();
        BeanUtils.copyProperties(riesgo, riesgoEntity);
        return riesgoEntity;
    }
}
