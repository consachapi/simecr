package pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;

import javax.persistence.*;

@Entity
@Table(name = "oficina")
public class OficinaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ofi_id")
    private Long id;

    @Column(name = "ofi_abrev")
    private String abreviatura;

    @Column(name = "ofi_desc")
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Oficina toOficina(){
        Oficina oficina = new Oficina();
        BeanUtils.copyProperties(this, oficina);
        return oficina;
    }

}
