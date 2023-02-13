package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.commons;

import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.commons.ComponenteCommon;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.ComponenteEntity;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities.DeficienciaEntity;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.Deficiencia;

public final class DeficienciaCommon {
    public static DeficienciaEntity toDeficienciaEntity(Deficiencia deficiencia){
        ComponenteEntity componenteEntity = ComponenteCommon.toComponenteEntity(deficiencia.getComponente());

        DeficienciaEntity entity = new DeficienciaEntity();
        entity.setId(deficiencia.getId());
        entity.setDeficiencia(deficiencia.getDeficiencia());
        entity.setComponente(componenteEntity);
        entity.setObservacion(deficiencia.getObservacion());
        entity.setPeriodo(deficiencia.getPeriodo());
        entity.setEstado(deficiencia.getEstado());
        return entity;
    }
}
