package pe.regioncusco.gob.simecr.security.domains.models;

import pe.regioncusco.gob.simecr.application.configuracion.domain.models.UnidadEjecutoria;

import java.util.List;

public class Usuario {
    private String usuario;
    private String nombres;
    private String nombresCompleto;
    private String cargo;
    private String role;
    private Integer anio;
    private List<UnidadEjecutoria> unidadEjecutorias;

    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombresCompleto() {
        return nombresCompleto;
    }

    public void setNombresCompleto(String nombresCompleto) {
        this.nombresCompleto = nombresCompleto;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public List<UnidadEjecutoria> getUnidadEjecutorias() {
        return unidadEjecutorias;
    }

    public void setUnidadEjecutorias(List<UnidadEjecutoria> unidadEjecutorias) {
        this.unidadEjecutorias = unidadEjecutorias;
    }

}
