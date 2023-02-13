package pe.regioncusco.gob.simecr.security.domains.models;

import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;

public class Persona {
    private String ndocumento;
    private String nombres;
    private String apellidos;
    private Oficina area;
    private Cargo cargo;
    private String telefono;
    private String email;
    private String direccion;
    private Perfil perfil;
    private String unidadEjecutora;
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

    public String getUnidadEjecutora() {
        return unidadEjecutora;
    }

    public void setUnidadEjecutora(String unidadEjecutora) {
        this.unidadEjecutora = unidadEjecutora;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
