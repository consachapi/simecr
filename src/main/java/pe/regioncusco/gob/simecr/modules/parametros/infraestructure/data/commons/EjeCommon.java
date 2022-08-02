package pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Eje;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.EjeEntity;

public final class EjeCommon {
    public static EjeEntity toEjeEntity(Eje eje){
        EjeEntity ejeEntity = new EjeEntity();
        BeanUtils.copyProperties(eje, ejeEntity);
        return ejeEntity;
    }
}
