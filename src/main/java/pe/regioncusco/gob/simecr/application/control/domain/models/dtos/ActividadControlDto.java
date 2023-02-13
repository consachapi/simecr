package pe.regioncusco.gob.simecr.modules.control.domain.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.ActividadEstado;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ActividadControlDto {
    private Long id;
    private String actividad;
    private ControlVerificacionDto controlVerificacion;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone="America/Lima")
    private Date fecha;

    private ActividadEstado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public ControlVerificacionDto getControlVerificacion() {
        return controlVerificacion;
    }

    public void setControlVerificacion(ControlVerificacionDto controlVerificacion) {
        this.controlVerificacion = controlVerificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ActividadEstado getEstado() {
        return estado;
    }

    public void setEstado(ActividadEstado estado) {
        this.estado = estado;
    }
}
