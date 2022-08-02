package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.Deficiencia;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.MedidasRemediacion;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medida_remediacion")
public class MedidasRemediacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mr_id")
    private Long id;

    @Column(name = "mr_remediacion")
    private String remediacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mr_deficiencia")
    private DeficienciaEntity deficiencia;

    @Column(name = "mr_fechr")
    private Date fechaRegistro;

    @Column(name = "mr_usuario")
    private String usuario;

    @Column(name = "mr_estado")
    private Integer estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemediacion() {
        return remediacion;
    }

    public void setRemediacion(String remediacion) {
        this.remediacion = remediacion;
    }

    public DeficienciaEntity getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(DeficienciaEntity deficiencia) {
        this.deficiencia = deficiencia;
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

    public MedidasRemediacion toMedidasRemediacion(){
        MedidasRemediacion medidasRemediacion = new MedidasRemediacion();
        BeanUtils.copyProperties(this, medidasRemediacion);

        Deficiencia deficiencia = this.deficiencia.toDeficiencia();
        medidasRemediacion.setDeficiencia(deficiencia);
        return medidasRemediacion;
    }

}
