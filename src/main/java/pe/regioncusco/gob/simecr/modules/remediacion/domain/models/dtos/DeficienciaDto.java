package pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos;

import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Componente;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.enums.DeficienciaEstado;

public class DeficienciaDto {
    private Long id;
    private String deficiencia;
    private Componente componente;
    private String observacion;
    private DeficienciaEstado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public DeficienciaEstado getEstado() {
        return estado;
    }

    public void setEstado(DeficienciaEstado estado) {
        this.estado = estado;
    }
}
