package pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.control.domain.models.MedidasControl;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medidas_control")
public class MedidasControlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_id")
    private Long id;

    @Column(name = "med_medida")
    private String mcontrol;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "med_riesgo")
    private RiesgoEntity riesgo;

    @Column(name = "med_fechr")
    private Date fechaRegistro;

    @Column(name = "med_usuario")
    private String usuario;

    @Column(name = "med_estado")
    private Integer estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMcontrol() {
        return mcontrol;
    }

    public void setMcontrol(String mcontrol) {
        this.mcontrol = mcontrol;
    }

    public RiesgoEntity getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(RiesgoEntity riesgo) {
        this.riesgo = riesgo;
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

    public MedidasControl toMedidasControl(){
        MedidasControl medidasControl = new MedidasControl();
        BeanUtils.copyProperties(this, medidasControl);
        medidasControl.setRiesgo(this.riesgo.toRiesgo());
        return medidasControl;
    }
}
