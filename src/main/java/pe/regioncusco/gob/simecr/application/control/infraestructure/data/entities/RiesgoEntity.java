package pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.control.domain.models.Riesgo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "riesgo")
public class RiesgoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ri_id")
    private Long id;

    @Column(name = "ri_riesgo")
    private String riesgo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ri_producto")
    private ProductoEntity producto;

    @Column(name = "ri_periodo")
    private Integer periodo;

    @Column(name = "ri_probab")
    private Integer probabilidad;

    @Column(name = "ri_impacto")
    private Integer impacto;

    @Column(name = "ri_fechr")
    private Date fechaRegsitro;

    @Column(name = "ri_usuario")
    private String usuario;

    @Column(name = "ri_estado")
    private Integer estado;

    @Column(name = "ri_autor")
    private String autor;

    @Column(name = "ri_oficina")
    private String oficina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Integer getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Integer probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Integer getImpacto() {
        return impacto;
    }

    public void setImpacto(Integer impacto) {
        this.impacto = impacto;
    }

    public Date getFechaRegsitro() {
        return fechaRegsitro;
    }

    public void setFechaRegsitro(Date fechaRegsitro) {
        this.fechaRegsitro = fechaRegsitro;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public Riesgo toRiesgo(){
        Riesgo riesgo = new Riesgo();
        BeanUtils.copyProperties(this, riesgo);
        if(this.producto != null){
            riesgo.setProducto(this.producto.toProducto());
        }
        return riesgo;
    }
}
