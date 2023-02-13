package pe.regioncusco.gob.simecr.modules.control.domain.models;

import java.util.Date;

public class ActividadControl {
    private Long id;
    private String actividad;
    private ControlVerificacion controlVerificacion;
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

    public ControlVerificacion getControlVerificacion() {
        return controlVerificacion;
    }

    public void setControlVerificacion(ControlVerificacion controlVerificacion) {
        this.controlVerificacion = controlVerificacion;
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
