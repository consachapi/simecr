package pe.regioncusco.gob.simecr.modules.documento.control.domain.models;

import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;

import java.util.Date;

public class DocumentoControl {
    private Long id;
    private ActividadControl actividadControl;
    private String nombreAchivo;
    private String archivo;
    private Date fechaRegistro;
    private Persona responsable;
    private Integer estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActividadControl getActividadControl() {
        return actividadControl;
    }

    public void setActividadControl(ActividadControl actividadControl) {
        this.actividadControl = actividadControl;
    }

    public String getNombreAchivo() {
        return nombreAchivo;
    }

    public void setNombreAchivo(String nombreAchivo) {
        this.nombreAchivo = nombreAchivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Persona getResponsable() {
        return responsable;
    }

    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
