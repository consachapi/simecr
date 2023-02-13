package pe.regioncusco.gob.simecr.application.remediacion.domain.models;

import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;

import java.util.Date;
import java.util.List;

public class RemediacionVerificacion {
    private Long id;
    private String medioVerificacion;
    private MedidasRemediacion medidasRemediacion;
    private Date inicio;
    private Date fin;
    private Integer estado;
    private List<Oficina> oficinas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedioVerificacion() {
        return medioVerificacion;
    }

    public void setMedioVerificacion(String medioVerificacion) {
        this.medioVerificacion = medioVerificacion;
    }

    public MedidasRemediacion getMedidasRemediacion() {
        return medidasRemediacion;
    }

    public void setMedidasRemediacion(MedidasRemediacion medidasRemediacion) {
        this.medidasRemediacion = medidasRemediacion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<Oficina> oficinas) {
        this.oficinas = oficinas;
    }
}
