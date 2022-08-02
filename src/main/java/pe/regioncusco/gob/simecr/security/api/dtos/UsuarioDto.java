package pe.regioncusco.gob.simecr.security.api.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UsuarioDto {
    @NotEmpty(message = "El usuario es obligatorio.")
    private String username;

    @NotEmpty(message = "El nombre es obligatorio.")
    private String firstname;

    @NotEmpty(message = "Los apellidos son obligatorios.")
    private String lastname;

    @NotEmpty(message = "La contraseña es obligatorio.")
    private String password;

    @Email(message = "Email debe ser valido.")
    private String email;

    @NotEmpty(message = "El perfil es obligatorio.")
    private String perfil;

    @NotNull
    private String ipress;

    public UsuarioDto(String username, String firstname, String lastname, String password, String email, String perfil, String ipress) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.perfil = perfil;
        this.ipress = ipress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getIpress() {
        return ipress;
    }

    public void setIpress(String ipress) {
        this.ipress = ipress;
    }
}
