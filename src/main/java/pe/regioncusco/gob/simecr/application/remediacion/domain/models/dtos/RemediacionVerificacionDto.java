package pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.application.remediacion.domain.enums.RemediacionVerificacionEstado;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class RemediacionVerificacionDto {
    private Long id;
    private String medioVerificacion;
    private MedidasRemediacionDto medidasRemediacion;
    private List<Oficina> oficinas;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="America/Lima")
    private Date inicio;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="America/Lima")
    private Date fin;

    private RemediacionVerificacionEstado estado;

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

    public MedidasRemediacionDto getMedidasRemediacion() {
        return medidasRemediacion;
    }

    public void setMedidasRemediacion(MedidasRemediacionDto medidasRemediacion) {
        this.medidasRemediacion = medidasRemediacion;
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<Oficina> oficinas) {
        this.oficinas = oficinas;
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

    public RemediacionVerificacionEstado getEstado() {
        return estado;
    }

    public void setEstado(RemediacionVerificacionEstado estado) {
        this.estado = estado;
    }
}
