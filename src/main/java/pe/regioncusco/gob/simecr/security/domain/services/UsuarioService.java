package pe.regioncusco.gob.simecr.security.domain.services;

import org.keycloak.representations.idm.UserRepresentation;
import pe.regioncusco.gob.simecr.security.api.dtos.UserDto;
import pe.regioncusco.gob.simecr.security.api.dtos.UsuarioDto;
import pe.regioncusco.gob.simecr.security.domain.models.Usuario;

import java.util.List;
import java.util.Map;

public interface UsuarioService {
    Usuario getPerfilUsuario();
    UserDto create(UsuarioDto usuario);
    UserRepresentation findByUsername(String username);
    UserDto reset(String usuario, Map<String, Object> password);
    void setEnabled(String username, boolean enabled);
    void setEnabledAndPerfil(String username, boolean enabled, String perfil);
    void changePerfil(String username, String perfil);
    void removePerfil(String username, String perfil);
}
