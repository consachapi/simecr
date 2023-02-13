package pe.regioncusco.gob.simecr.application.control.domain.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import pe.regioncusco.gob.simecr.application.control.domain.enums.ControlVerificacionEstado;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public class ControlVerificacionDto {
    private Long id;
    private String medioVerificacion;
    private MedidasControlDto medidasControl;
    private List<Oficina> oficinas;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="America/Lima")
    private Date inicio;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="America/Lima")
    private Date fin;

    private ControlVerificacionEstado estado;

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

    public MedidasControlDto getMedidasControl() {
        return medidasControl;
    }

    public void setMedidasControl(MedidasControlDto medidasControl) {
        this.medidasControl = medidasControl;
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

    public ControlVerificacionEstado getEstado() {
        return estado;
    }

    public void setEstado(ControlVerificacionEstado estado) {
        this.estado = estado;
    }

    public List<Oficina> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<Oficina> oficinas) {
        this.oficinas = oficinas;
    }
}
