package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.OficinaEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "control_verificacion")
public class ControlVerificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cv_id")
    private Long id;

    @Column(name = "cv_mverificacion")
    private String medioVerificacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cv_mcontrol")
    private MedidasControlEntity medidasControl;

    @Column(name = "cv_inicio")
    private Date inicio;

    @Column(name = "cv_fin")
    private Date fin;

    @Column(name = "cv_fechr")
    private Date fechaRegistro;

    @Column(name = "cv_user")
    private String usuario;

    @Column(name = "cv_estado")
    private Integer estado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cverificacion_oficina",
            joinColumns = { @JoinColumn(name = "cvo_cverif", referencedColumnName = "cv_id") },
            inverseJoinColumns = { @JoinColumn(name = "cvo_oficina", referencedColumnName = "ofi_id") }
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

    public MedidasControlEntity getMedidasControl() {
        return medidasControl;
    }

    public void setMedidasControl(MedidasControlEntity medidasControl) {
        this.medidasControl = medidasControl;
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

    public ControlVerificacion toControlVerificacion(){
        ControlVerificacion controlVerificacion = new ControlVerificacion();
        BeanUtils.copyProperties(this, controlVerificacion);
        if(this.medidasControl != null){
            controlVerificacion.setMedidasControl(this.medidasControl.toMedidasControl());
        }

        if(this.oficinas != null){
            List<Oficina> oficinas = this.oficinas.stream().map(oficinaEntity -> oficinaEntity.toOficina()).collect(Collectors.toList());
            controlVerificacion.setOficinas(oficinas);
        }
        return controlVerificacion;
    }
}
