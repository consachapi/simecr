package pe.regioncusco.gob.simecr.modules.control.domain.models.dtos;

import pe.regioncusco.gob.simecr.modules.control.domain.enums.ProductoEstado;

public class ProductoDto {
    private Long id;
    private String descripcion;
    private Double presupuesto;
    private Double porcentaje;
    private Integer periodo;
    private String observacion;
    private ProductoEstado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public ProductoEstado getEstado() {
        return estado;
    }

    public void setEstado(ProductoEstado estado) {
        this.estado = estado;
    }
}
