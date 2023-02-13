package pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.commons;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.ComponenteEntity;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.EjeEntity;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Componente;

public final class ComponenteCommon {
    public static ComponenteEntity toComponenteEntity(Componente componente){
        ComponenteEntity componenteEntity = new ComponenteEntity();
        BeanUtils.copyProperties(componente, componenteEntity);
        EjeEntity ejeEntity = EjeCommon.toEjeEntity(componente.getEje());
        componenteEntity.setEje(ejeEntity);
        return componenteEntity;
    }
}
