package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.DeficienciaEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.MedidasRemediacionEntity;

public final class MedidasRemediacionAdapterCommon {
    public static MedidasRemediacionEntity toMedidasRemediacionEntity(MedidasRemediacion medidasRemediacion){
        MedidasRemediacionEntity medidasRemediacionEntity = new MedidasRemediacionEntity();
        BeanUtils.copyProperties(medidasRemediacion, medidasRemediacionEntity);
        DeficienciaEntity deficienciaEntity = DeficienciaCommon.toDeficienciaEntity(medidasRemediacion.getDeficiencia());
        medidasRemediacionEntity.setDeficiencia(deficienciaEntity);
        return medidasRemediacionEntity;
    }
}
