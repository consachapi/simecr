package pe.regioncusco.gob.simecr.security.data.entities;

import org.springframework.beans.BeanUtils;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.OficinaEntity;
import pe.regioncusco.gob.simecr.security.domain.models.Cargo;
import pe.regioncusco.gob.simecr.security.domain.models.Perfil;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persona")
public class PersonaEntity {
    @Id
    @Column(name = "pe_id")
    private String ndocumento;

    @Column(name = "pe_nombres")
    private String nombres;

    @Column(name = "pe_apellidos")
    private String apellidos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pe_area")
    private OficinaEntity area;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pe_cargo")
    private CargoEntity cargo;

    @Column(name = "pe_telefono")
    private String telefono;

    @Column(name = "pe_email")
    private String email;

    @Column(name = "pe_direccion")
    private String direccion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pe_perfil")
    private PerfilEntity perfil;

    @Column(name = "pe_fechr")
    private Date fechaRegistro;

    @Column(name = "pe_usuario")
    private String usuario;

    @Column(name = "pe_estado")
    private boolean active;

    public String getNdocumento() {
        return ndocumento;
    }

    public void setNdocumento(String ndocumento) {
        this.ndocumento = ndocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public OficinaEntity getArea() {
        return area;
    }

    public void setArea(OficinaEntity area) {
        this.area = area;
    }

    public CargoEntity getCargo() {
        return cargo;
    }

    public void setCargo(CargoEntity cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public PerfilEntity getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEntity perfil) {
        this.perfil = perfil;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Persona toPersona(){
        Persona persona = new Persona();
        Cargo cargo = this.cargo.toCargo();
        Perfil perfil = this.getPerfil().toPerfil();
        Oficina oficina = this.area.toOficina();
        BeanUtils.copyProperties(this, persona);
        persona.setArea(oficina);
        persona.setCargo(cargo);
        persona.setPerfil(perfil);
        return persona;
    }
}
