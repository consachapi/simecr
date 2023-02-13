package pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Componente;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Eje;

import javax.persistence.*;

@Entity
@Table(name = "componente")
public class ComponenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "com_eje")
    private EjeEntity eje;

    @Column(name = "com_desc")
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EjeEntity getEje() {
        return eje;
    }

    public void setEje(EjeEntity eje) {
        this.eje = eje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Componente toComponente(){
        Componente componente =  new Componente();
        BeanUtils.copyProperties(this, componente);
        Eje eje = this.eje.toEje();
        componente.setEje(eje);
        return componente;
    }

}
