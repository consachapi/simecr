package pe.regioncusco.gob.simecr.security.applications.services;

import org.keycloak.representations.idm.UserRepresentation;
import pe.regioncusco.gob.simecr.security.domains.dtos.UserDto;
import pe.regioncusco.gob.simecr.security.domains.dtos.UsuarioDto;
import pe.regioncusco.gob.simecr.security.domains.models.Usuario;

import java.util.Map;

public interface UsuarioService {
    Usuario getPerfilUsuario();
    void logout(String session);



    UserDto create(UsuarioDto usuario);
    UserRepresentation findByUsername(String username);
    UserDto reset(String usuario, Map<String, Object> password);
    void setEnabled(String username, boolean enabled);
    void setEnabledAndPerfil(String username, boolean enabled, String perfil);
    void changePerfil(String username, String perfil);
    void removePerfil(String username, String perfil);
}
