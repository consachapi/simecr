package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "actividad_control")
public class ActividadControlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Long id;

    @Column(name = "acc_actividad")
    private String actividad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "acc_cverif")
    private ControlVerificacionEntity controlVerificacion;

    @Column(name = "acc_fecha")
    private Date fecha;

    @Column(name = "acc_fechr")
    private Date fechaRegistro;

    @Column(name = "acc_user")
    private String usuario;

    @Column(name = "acc_estado")
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

    public ControlVerificacionEntity getControlVerificacion() {
        return controlVerificacion;
    }

    public void setControlVerificacion(ControlVerificacionEntity controlVerificacion) {
        this.controlVerificacion = controlVerificacion;
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

    public ActividadControl toActividadControl(){
        ActividadControl actividadControl = new ActividadControl();
        BeanUtils.copyProperties(this, actividadControl);
        if(this.controlVerificacion != null){
            ControlVerificacion controlVerificacion = this.controlVerificacion.toControlVerificacion();
            actividadControl.setControlVerificacion(controlVerificacion);
        }
        return actividadControl;
    }
}
