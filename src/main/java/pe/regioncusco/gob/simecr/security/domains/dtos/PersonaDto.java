package pe.regioncusco.gob.simecr.security.domains.dtos;

import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.security.domains.models.Cargo;
import pe.regioncusco.gob.simecr.security.domains.models.Perfil;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;

public class PersonaDto {
    private String ndocumento;
    private String nombres;
    private String apellidos;
    private Oficina area;
    private Cargo cargo;
    private String telefono;
    private String email;
    private String direccion;
    private Perfil perfil;
    private boolean active;

    public PersonaDto() {
    }

    public PersonaDto(Persona persona) {
        ndocumento = persona.getNdocumento();
        nombres = persona.getNombres();
        apellidos = persona.getApellidos();
        area = persona.getArea();
        cargo = persona.getCargo();
        telefono = persona.getTelefono();
        email = persona.getEmail();
        direccion = persona.getDireccion();
        perfil = persona.getPerfil();
        active = persona.isActive();
    }

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

    public Oficina getArea() {
        return area;
    }

    public void setArea(Oficina area) {
        this.area = area;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
