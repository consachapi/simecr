package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.MedidasControlEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.RiesgoEntity;

public final class MedidaControlAdapterCommon {
    public static MedidasControlEntity toMedidasControlEntity(MedidasControl medidasControl){
        MedidasControlEntity medidasControlEntity = new MedidasControlEntity();
        BeanUtils.copyProperties(medidasControl, medidasControlEntity);
        RiesgoEntity riesgoEntity = RiesgoAdapterCommon.toRiesgoEntity(medidasControl.getRiesgo());
        medidasControlEntity.setRiesgo(riesgoEntity);
        return medidasControlEntity;
    }
}
