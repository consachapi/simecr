package pe.regioncusco.gob.simecr.modules.control.domain.models;

public class MedidasControl {
    private Long id;
    private String mcontrol;
    private Riesgo riesgo;
    private Integer estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMcontrol() {
        return mcontrol;
    }

    public void setMcontrol(String mcontrol) {
        this.mcontrol = mcontrol;
    }

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

}
