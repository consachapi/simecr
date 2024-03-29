package pe.regioncusco.gob.simecr.security.domains.commons;

import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PerfilEntity;
import pe.regioncusco.gob.simecr.security.domains.models.Perfil;

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
