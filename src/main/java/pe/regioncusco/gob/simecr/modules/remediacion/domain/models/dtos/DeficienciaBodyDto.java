package pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos;

public class DeficienciaBodyDto {
    private String deficiencia;
    private Long componente;
    private String observacion;
    private Integer estado;

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public Long getComponente() {
        return componente;
    }

    public void setComponente(Long componente) {
        this.componente = componente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

}
