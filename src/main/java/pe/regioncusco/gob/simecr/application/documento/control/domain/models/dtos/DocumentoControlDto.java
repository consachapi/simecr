package pe.regioncusco.gob.simecr.application.documento.control.domain.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import pe.regioncusco.gob.simecr.application.control.domain.models.dtos.ActividadControlDto;
import pe.regioncusco.gob.simecr.application.documento.control.domain.enums.DocumentoEstado;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class DocumentoControlDto {
    private Long id;
    private ActividadControlDto actividadControl;
    private String nombreAchivo;
    private String archivo;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone="America/Lima")
    private Date fechaRegistro;

    private Persona responsable;
    private DocumentoEstado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActividadControlDto getActividadControl() {
        return actividadControl;
    }

    public void setActividadControl(ActividadControlDto actividadControl) {
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

    public DocumentoEstado getEstado() {
        return estado;
    }

    public void setEstado(DocumentoEstado estado) {
        this.estado = estado;
    }
}
