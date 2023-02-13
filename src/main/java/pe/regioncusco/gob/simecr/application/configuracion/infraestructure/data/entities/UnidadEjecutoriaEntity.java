package pe.regioncusco.gob.simecr.application.configuracion.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.application.configuracion.domain.models.UnidadEjecutoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unidad_ejecutora")
public class UnidadEjecutoriaEntity {
    @Id
    @Column(name = "ue_id")
    private String codigo;

    @Column(name = "ue_desc")
    private String descripcion;

    @Column(name = "ue_st")
    private boolean enabled;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UnidadEjecutoria toUnidadEjecutoria(){
        UnidadEjecutoria unidadEjecutoria = new UnidadEjecutoria();
        BeanUtils.copyProperties(this, unidadEjecutoria);
        return unidadEjecutoria;
    }

}
