package pe.regioncusco.gob.simecr.security.infraestructures.database.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.security.domains.models.Cargo;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
public class CargoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ca_id")
    private Long id;

    @Column(name = "ca_desc")
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

    public Cargo toCargo(){
        Cargo cargo = new Cargo();
        BeanUtils.copyProperties(this, cargo);
        return cargo;
    }
}
