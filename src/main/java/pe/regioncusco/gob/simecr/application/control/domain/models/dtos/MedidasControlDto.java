package pe.regioncusco.gob.simecr.modules.control.domain.models.dtos;

import pe.regioncusco.gob.simecr.modules.control.domain.enums.MedidasControlEstado;

public class MedidasControlDto {
    private Long id;
    private String mcontrol;
    private RiesgoDto riesgo;
    private MedidasControlEstado estado;

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

    public RiesgoDto getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(RiesgoDto riesgo) {
        this.riesgo = riesgo;
    }

    public MedidasControlEstado getEstado() {
        return estado;
    }

    public void setEstado(MedidasControlEstado estado) {
        this.estado = estado;
    }
}
