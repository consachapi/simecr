package pe.regioncusco.gob.simecr.modules.control.domain.models.dtos;

import pe.regioncusco.gob.simecr.modules.control.domain.enums.ImpactoEnum;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.NivelEnum;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.ProbabilidadEnum;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.RiesgoEstado;

public class RiesgoDto {
    private Long id;
    private String riesgo;
    private ProductoDto producto;
    private Integer periodo;
    private ProbabilidadEnum probabilidad;
    private ImpactoEnum impacto;
    private Integer valor;
    private NivelEnum nivel;
    private RiesgoEstado estado;
    private String autor;
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

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public ProbabilidadEnum getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(ProbabilidadEnum probabilidad) {
        this.probabilidad = probabilidad;
    }

    public ImpactoEnum getImpacto() {
        return impacto;
    }

    public void setImpacto(ImpactoEnum impacto) {
        this.impacto = impacto;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public NivelEnum getNivel() {
        return nivel;
    }

    public void setNivel(NivelEnum nivel) {
        this.nivel = nivel;
    }

    public RiesgoEstado getEstado() {
        return estado;
    }

    public void setEstado(RiesgoEstado estado) {
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
}
