package pe.regioncusco.gob.simecr.application.configuracion.infraestructure.database.commons;

import pe.regioncusco.gob.simecr.application.configuracion.domain.models.UnidadEjecutoria;
import pe.regioncusco.gob.simecr.application.configuracion.infraestructure.database.entities.UnidadEjecutoriaEntity;

public final class UnidadEjecutoraCommonData {
    public static UnidadEjecutoriaEntity toEntity(UnidadEjecutoria unidadEjecutoria){
        UnidadEjecutoriaEntity unidadEjecutoriaEntity = new UnidadEjecutoriaEntity();
        unidadEjecutoriaEntity.setCodigo(unidadEjecutoria.getCodigo());
        unidadEjecutoriaEntity.setDescripcion(unidadEjecutoria.getDescripcion());
        unidadEjecutoriaEntity.setEnabled(unidadEjecutoria.isEnabled());
        return unidadEjecutoriaEntity;
    }
}
