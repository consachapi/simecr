package pe.regioncusco.gob.simecr.application.documento.remediacion.domain.models;

import pe.regioncusco.gob.simecr.application.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;

import java.util.Date;

public class DocumentoRemediacion {
    private Long id;
    private ActividadRemediacion actividadRemediacion;
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

    public ActividadRemediacion getActividadRemediacion() {
        return actividadRemediacion;
    }

    public void setActividadRemediacion(ActividadRemediacion actividadRemediacion) {
        this.actividadRemediacion = actividadRemediacion;
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
