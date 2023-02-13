package pe.regioncusco.gob.simecr.security.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.security.domain.models.Perfil;

import javax.persistence.*;

@Entity
@Table(name = "perfil")
public class PerfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pe_id")
    private Long id;

    @Column(name = "pe_desc")
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

    public Perfil toPerfil(){
        Perfil perfil = new Perfil();
        BeanUtils.copyProperties(this, perfil);
        return perfil;
    }
}
