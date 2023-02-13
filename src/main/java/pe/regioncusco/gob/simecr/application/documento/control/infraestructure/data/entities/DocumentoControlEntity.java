package pe.regioncusco.gob.simecr.application.documento.control.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.ActividadControlEntity;
import pe.regioncusco.gob.simecr.application.documento.control.domain.models.DocumentoControl;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PersonaEntity;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documento_control")
public class DocumentoControlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dc_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dc_accid")
    private ActividadControlEntity actividadControl;

    @Column(name = "dc_nombre")
    private String nombreAchivo;

    @Column(name = "dc_archivo")
    private String archivo;

    @Column(name = "dc_fechr")
    private Date fechaRegistro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dc_user")
    private PersonaEntity responsable;

    @Column(name = "dc_estado")
    private Integer estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActividadControlEntity getActividadControl() {
        return actividadControl;
    }

    public void setActividadControl(ActividadControlEntity actividadControl) {
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

    public PersonaEntity getResponsable() {
        return responsable;
    }

    public void setResponsable(PersonaEntity responsable) {
        this.responsable = responsable;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public DocumentoControl toDocumentoControl(){
        DocumentoControl documentoControl = new DocumentoControl();
        ActividadControl actividadControl = this.actividadControl.toActividadControl();
        Persona persona = this.responsable.toPersona();
        BeanUtils.copyProperties(this, documentoControl);
        documentoControl.setActividadControl(actividadControl);
        documentoControl.setResponsable(persona);
        return documentoControl;
    }
}
