package pe.regioncusco.gob.simecr.modules.configuracion.infraestructure.data.commons;

import pe.regioncusco.gob.simecr.modules.configuracion.domain.models.UnidadEjecutoria;
import pe.regioncusco.gob.simecr.modules.configuracion.infraestructure.data.entities.UnidadEjecutoriaEntity;

public final class UnidadEjecutoraCommonData {
    public static UnidadEjecutoriaEntity toEntity(UnidadEjecutoria unidadEjecutoria){
        UnidadEjecutoriaEntity unidadEjecutoriaEntity = new UnidadEjecutoriaEntity();
        unidadEjecutoriaEntity.setCodigo(unidadEjecutoria.getCodigo());
        unidadEjecutoriaEntity.setDescripcion(unidadEjecutoria.getDescripcion());
        unidadEjecutoriaEntity.setEnabled(unidadEjecutoria.isEnabled());
        return unidadEjecutoriaEntity;
    }
}
