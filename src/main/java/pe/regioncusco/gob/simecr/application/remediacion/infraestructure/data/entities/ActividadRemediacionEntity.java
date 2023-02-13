package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.RemediacionVerificacion;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actividad_remediacion")
public class ActividadRemediacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acr_id")
    private Long id;

    @Column(name = "acr_actividad")
    private String actividad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "acr_rverif")
    private RemediacionVerificacionEntity remediacionVerificacion;

    @Column(name = "acr_fecha")
    private Date fecha;

    @Column(name = "acr_fechr")
    private Date fechaRegistro;

    @Column(name = "acr_user")
    private String usuario;

    @Column(name = "acr_estado")
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

    public RemediacionVerificacionEntity getRemediacionVerificacion() {
        return remediacionVerificacion;
    }

    public void setRemediacionVerificacion(RemediacionVerificacionEntity remediacionVerificacion) {
        this.remediacionVerificacion = remediacionVerificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public ActividadRemediacion toActividadRemediacion(){
        ActividadRemediacion actividadRemediacion = new ActividadRemediacion();
        BeanUtils.copyProperties(this, actividadRemediacion);
        if(this.remediacionVerificacion != null){
            RemediacionVerificacion remediacionVerificacion = this.remediacionVerificacion.toRemediacionVerificacion();
            actividadRemediacion.setRemediacionVerificacion(remediacionVerificacion);
        }
        return actividadRemediacion;
    }
}
