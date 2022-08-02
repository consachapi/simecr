package pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Eje;

import javax.persistence.*;

@Entity
@Table(name = "eje")
public class EjeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eje_id")
    private Long id;

    @Column(name = "eje_desc")
    private String descripcion;

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

    public Eje toEje(){
        Eje eje = new Eje();
        BeanUtils.copyProperties(this, eje);
        return eje;
    }

}
