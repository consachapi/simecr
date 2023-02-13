package pe.regioncusco.gob.simecr.application.remediacion.domain.models;

public class MedidasRemediacion {
    private Long id;
    private String remediacion;
    private Deficiencia deficiencia;
    private Integer estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemediacion() {
        return remediacion;
    }

    public void setRemediacion(String remediacion) {
        this.remediacion = remediacion;
    }

    public Deficiencia getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(Deficiencia deficiencia) {
        this.deficiencia = deficiencia;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
