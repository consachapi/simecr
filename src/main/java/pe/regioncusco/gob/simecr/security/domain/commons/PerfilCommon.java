package pe.regioncusco.gob.simecr.security.domain.commons;

import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.security.data.entities.PerfilEntity;
import pe.regioncusco.gob.simecr.security.domain.models.Perfil;

public final class PerfilCommon {
    public static PerfilEntity toPerfilEntity(Perfil perfil){
        PerfilEntity perfilEntity = new PerfilEntity();
        perfilEntity.setId(perfil.getId());
        perfilEntity.setDescripcion(perfil.getDescripcion());
        return perfilEntity;
    }

    public static MyValue toMyValue(PerfilEntity perfilEntity){
        return new MyValue(perfilEntity.getId(), perfilEntity.getDescripcion());
    }
}
