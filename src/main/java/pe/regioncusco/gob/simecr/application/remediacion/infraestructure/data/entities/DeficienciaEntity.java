package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Componente;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.ComponenteEntity;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.Deficiencia;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deficiencia")
public class DeficienciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "def_id")
    private Long id;

    @Column(name = "def_deficiencia")
    private String deficiencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "def_componente")
    private ComponenteEntity componente;

    @Column(name = "def_obs")
    private String observacion;

    @Column(name = "def_periodo")
    private Integer periodo;

    @Column(name = "def_fechr")
    private Date fechaRegistro;

    @Column(name = "def_usuario")
    private String usuario;

    @Column(name = "def_estado")
    private Integer estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public ComponenteEntity getComponente() {
        return componente;
    }

    public void setComponente(ComponenteEntity componente) {
        this.componente = componente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
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

    public Deficiencia toDeficiencia(){
        Deficiencia deficiencia = new Deficiencia();
        Componente componente = this.getComponente().toComponente();
        BeanUtils.copyProperties(this, deficiencia);
        deficiencia.setComponente(componente);
        return deficiencia;
    }

}
