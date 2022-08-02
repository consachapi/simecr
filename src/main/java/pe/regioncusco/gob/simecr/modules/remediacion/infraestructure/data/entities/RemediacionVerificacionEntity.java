package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.OficinaEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.RemediacionVerificacion;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "remediacion_verificacion")
public class RemediacionVerificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rv_id")
    private Long id;

    @Column(name = "rv_mverificacion")
    private String medioVerificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rv_mremediacion")
    private MedidasRemediacionEntity medidasRemediacion;

    @Column(name = "rv_inicio")
    private Date inicio;

    @Column(name = "rv_fin")
    private Date fin;

    @Column(name = "rv_fechr")
    private Date fechaRegistro;

    @Column(name = "rv_user")
    private String usuario;

    @Column(name = "rv_estado")
    private Integer estado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rverificacion_oficina",
            joinColumns = { @JoinColumn(name = "rvo_rverif", referencedColumnName = "rv_id") },
            inverseJoinColumns = { @JoinColumn(name = "rvo_oficina", referencedColumnName = "ofi_id") }
    )
    private List<OficinaEntity> oficinas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedioVerificacion() {
        return medioVerificacion;
    }

    public void setMedioVerificacion(String medioVerificacion) {
        this.medioVerificacion = medioVerificacion;
    }

    public MedidasRemediacionEntity getMedidasRemediacion() {
        return medidasRemediacion;
    }

    public void setMedidasRemediacion(MedidasRemediacionEntity medidasRemediacion) {
        this.medidasRemediacion = medidasRemediacion;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
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

    public List<OficinaEntity> getOficinas() {
        return oficinas;
    }

    public void setOficinas(List<OficinaEntity> oficinas) {
        this.oficinas = oficinas;
    }

    public RemediacionVerificacion toRemediacionVerificacion(){
        RemediacionVerificacion remediacionVerificacion = new RemediacionVerificacion();
        BeanUtils.copyProperties(this, remediacionVerificacion);
        if(this.medidasRemediacion != null){
            remediacionVerificacion.setMedidasRemediacion(this.medidasRemediacion.toMedidasRemediacion());
        }

        if(this.oficinas != null){
            List<Oficina> oficinas = this.oficinas.stream().map(oficinaEntity -> oficinaEntity.toOficina()).collect(Collectors.toList());
            remediacionVerificacion.setOficinas(oficinas);
        }
        return remediacionVerificacion;
    }

}
