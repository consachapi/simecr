package pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.commons;

import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.OficinaEntity;

public final class OficinaCommon {
    public static OficinaEntity toOficinaEntity(Oficina oficina){
        OficinaEntity oficinaEntity = new OficinaEntity();
        oficinaEntity.setId(oficina.getId());
        oficinaEntity.setAbreviatura(oficina.getAbreviatura());
        oficinaEntity.setDescripcion(oficina.getDescripcion());
        return oficinaEntity;
    }
}
