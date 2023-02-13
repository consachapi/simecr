package pe.regioncusco.gob.simecr.application.remediacion.domain.models;

import java.util.Date;

public class ActividadRemediacion {
    private Long id;
    private String actividad;
    private RemediacionVerificacion remediacionVerificacion;
    private Date fecha;
    private Integer estado;

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

    public RemediacionVerificacion getRemediacionVerificacion() {
        return remediacionVerificacion;
    }

    public void setRemediacionVerificacion(RemediacionVerificacion remediacionVerificacion) {
        this.remediacionVerificacion = remediacionVerificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
