package pe.regioncusco.gob.simecr.application.documento.remediacion.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.documento.remediacion.domain.models.DocumentoRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities.ActividadRemediacionEntity;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PersonaEntity;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documento_remediacion")
public class DocumentoRemediacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dr_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dr_acrid")
    private ActividadRemediacionEntity actividadRemediacion;

    @Column(name = "dr_nombre")
    private String nombreAchivo;

    @Column(name = "dr_archivo")
    private String archivo;

    @Column(name = "dr_fechr")
    private Date fechaRegistro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dr_user")
    private PersonaEntity responsable;

    @Column(name = "dr_estado")
    private Integer estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActividadRemediacionEntity getActividadRemediacion() {
        return actividadRemediacion;
    }

    public void setActividadRemediacion(ActividadRemediacionEntity actividadRemediacion) {
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

    public DocumentoRemediacion toDocumentoRemediacion(){
        DocumentoRemediacion documentoRemediacion = new DocumentoRemediacion();
        ActividadRemediacion actividadRemediacion = this.actividadRemediacion.toActividadRemediacion();
        Persona persona = this.responsable.toPersona();
        BeanUtils.copyProperties(this, documentoRemediacion);
        documentoRemediacion.setActividadRemediacion(actividadRemediacion);
        documentoRemediacion.setResponsable(persona);
        return documentoRemediacion;
    }
}
