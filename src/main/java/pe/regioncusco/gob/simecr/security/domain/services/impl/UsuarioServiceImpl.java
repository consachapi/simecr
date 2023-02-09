package pe.regioncusco.gob.simecr.security.domain.services.impl;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.commons.MyValueString;
import pe.regioncusco.gob.simecr.commons.ParamsManager;
import pe.regioncusco.gob.simecr.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.config.UtilConfig;
import pe.regioncusco.gob.simecr.exceptions.BadRequestException;
import pe.regioncusco.gob.simecr.exceptions.ConflictException;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.modules.configuracion.domain.models.UnidadEjecutoria;
import pe.regioncusco.gob.simecr.modules.configuracion.domain.services.UnidadEjecutoraService;
import pe.regioncusco.gob.simecr.security.api.dtos.UserDto;
import pe.regioncusco.gob.simecr.security.api.dtos.UsuarioDto;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;
import pe.regioncusco.gob.simecr.security.domain.models.Usuario;
import pe.regioncusco.gob.simecr.security.domain.services.PersonaService;
import pe.regioncusco.gob.simecr.security.domain.services.UsuarioService;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private static final String ADMIN_CARGO = "Administrador del Sistema";

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private UtilConfig config;
    @Autowired private PersonaService personaService;
    @Autowired private UnidadEjecutoraService unidadEjecutoraService;

    @Override
    public Usuario getPerfilUsuario() {
        if(accessToken.isAnonymous()){
            throw new NotFoundException("Usuario no existe");
        }
        String username = accessToken.getUserId();
        Persona persona = personaService.findById(username);
        Set<String> roles = accessToken.getUserRole();
        if(roles.isEmpty()){
            LOG.warn("Usuario {} sin rol.", accessToken.getUserId());
            throw new NotFoundException("Usuario no tiene rol.");
        }

        List<UnidadEjecutoria> unidadEjecutorias = new ArrayList<>();
        if(accessToken.getEjecCodigo().equals("000")){
            unidadEjecutorias = unidadEjecutoraService.findAllActive();
        } else {
            unidadEjecutorias.add(unidadEjecutoraService.findUnidadEjecutoraById(accessToken.getEjecCodigo()));
        }

        Usuario usuario = new Usuario();
        if(username.equals("admin")){
            UserRepresentation userRepresentation = findByUsername(username);
            usuario.setUsuario(username);
            usuario.setNombres(userRepresentation.getFirstName());
            usuario.setNombresCompleto(userRepresentation.getFirstName() + " del " + userRepresentation.getLastName());
            usuario.setCargo(ADMIN_CARGO);
            usuario.setRole("SUPER");
            usuario.setUnidadEjecutorias(unidadEjecutorias);
            usuario.setAnio(2023);
            return usuario;
        }

        usuario.setUsuario(username);
        usuario.setNombres(persona.getNombres());
        usuario.setNombresCompleto(persona.getNombres() + " " + persona.getApellidos());
        usuario.setCargo(persona.getCargo().getDescripcion());
        usuario.setUnidadEjecutorias(unidadEjecutorias);

        if(roles.contains(ParamsManager.REALM_ADMIN)){
            usuario.setRole("ADMIN");
            return usuario;
        }

        if(roles.contains(ParamsManager.REALM_RESP)){
            usuario.setRole("RESP");
            return usuario;
        }

        usuario.setRole("USER");
        return usuario;
    }











    @Override
    public UserDto create(UsuarioDto usuario) {
        Persona persona = personaService.findById(usuario.getUsername());
        LOG.info("Creando usuario para {} - {}", usuario.getUsername(), usuario.getFirstname());
        Keycloak keycloak = config.keycloak();
        RealmResource realmResource = keycloak.realm(ParamsManager.REALM);
        UsersResource usersRessource = realmResource.users();
        UserRepresentation userNuevo = new UserRepresentation();
        userNuevo.setEmailVerified(false);
        userNuevo.setUsername(persona.getNdocumento());
        userNuevo.setFirstName(usuario.getFirstname());
        userNuevo.setLastName(usuario.getLastname());
        userNuevo.setEmail(usuario.getEmail());
        userNuevo.setEnabled(true);

        Response resp = usersRessource.create(userNuevo);
        if(resp.getStatus() == 201){
            String userId = CreatedResponseUtil.getCreatedId(resp);

            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(usuario.getPassword());

            UserResource userResource = usersRessource.get(userId);
            userResource.resetPassword(passwordCred);

            userResource.roles().realmLevel().add(roles(usuario.getPerfil(), realmResource));

            LOG.info("Usuario CREADO con ID={}", userId);
            return new UserDto(persona.getNdocumento(), persona.getNombres(), persona.getApellidos(), persona.getEmail(), persona.getPerfil().getDescripcion());
        }
        if(resp.getStatus() == 409){
            LOG.warn("Error con estado {}", resp.getStatus());
            throw new ConflictException("Ya existe usuario " + usuario.getUsername());
        } else {
            LOG.warn("Error con estado {}", resp.getStatus());
            throw new BadRequestException("Ocurrio un error al crear el usuario.");
        }
    }

    @Override
    public UserRepresentation findByUsername(String username) {
        Keycloak keycloak = config.keycloak();
        Optional<UserRepresentation> user = keycloak.realm(ParamsManager.REALM)
                .users()
                .search(username)
                .stream().filter(u -> u.getUsername().equals(username)).findFirst();
        if(!user.isPresent()){
            throw new NotFoundException("Usuario " + username + ", no existe.");
        }
        return user.get();
    }

    /*
    @Override
    public List<UserRepresentation> findByUsername(String username) {
        Persona persona = personaService.findById(username);
        Keycloak keycloak = config.keycloak();
        List<UserRepresentation> users = keycloak.realm(ParamsManager.REALM).users().search(persona.getNdocumento());
        return users;
    }
*/
    @Override
    public UserDto reset(String usuario, Map<String, Object> password) {
        Keycloak keycloak = config.keycloak();
        RealmResource realmResource = keycloak.realm(ParamsManager.REALM);
        UsersResource usersRessource = realmResource.users();

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue((String) password.get("password"));

        try {
            UserResource userResource = usersRessource.get(usuario);
            userResource.resetPassword(passwordCred);

            LOG.info("Contraseña reseteado para {}", usuario);
            return new UserDto(
                    userResource.toRepresentation().getUsername(),
                    userResource.toRepresentation().getFirstName(),
                    userResource.toRepresentation().getLastName(),
                    userResource.toRepresentation().getEmail(),
                    ""
            );
        } catch (Exception ex){
            LOG.info("Error al resetear para {}, {}", usuario, ex.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al resetear");
        }
    }

    @Override
    public void setEnabled(String username, boolean enabled) {
        try {
            UserResource userResource = getUserResource(username);
            UserRepresentation userRepresentation = userResource.toRepresentation();
            userRepresentation.setEnabled(enabled);
            userResource.update(userRepresentation);
            LOG.info("ID {}, enabled {}", userResource.toRepresentation().getId(), enabled);
        } catch (Exception ex){
            LOG.info("Error al bloquear {}, {}", username, ex.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al bloquear");
        }
    }

    @Override
    public void setEnabledAndPerfil(String username, boolean enabled, String perfil) {
        try {
            UserResource userResource = getUserResource(username);
            List<RoleRepresentation> roles =  userResource.roles().realmLevel().listAll();
            LOG.info("Roles del usuario {}, {}", username, roles.size());

            userResource.roles().realmLevel().add(roles(perfil, getRealmResource()));
            LOG.info("ID {}, enabled {}", userResource.toRepresentation().getId(), enabled);
        } catch (Exception ex){
            LOG.info("Error al crear perfiles para {}, {}", username, ex.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al bloquear");
        }
    }

    @Override
    public void changePerfil(String username, String perfil) {
        try {
            UserResource userResource = getUserResource(username);
            List<RoleRepresentation> roles =  userResource.roles().realmLevel().listAll();
            LOG.info("Roles del usuario {}, {}", username, roles.size());

            userResource.roles().realmLevel().remove(roles);
            List<RoleRepresentation> rolesNuevos =  roles(perfil, getRealmResource());

            userResource.roles().realmLevel().add(roles);
            userResource.roles().realmLevel().add(rolesNuevos);
            LOG.info("ID {}, Perfil cambiado {}", userResource.toRepresentation().getId());
        } catch (Exception ex){
            LOG.info("Error al cambiar perfil del {}, {}", username, ex.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al bloquear");
        }
    }

    @Override
    public void removePerfil(String username, String perfil) {
        try {
            UserResource userResource = getUserResource(username);
            List<RoleRepresentation> roles =  userResource.roles().realmLevel().listAll();
            LOG.info("Roles del usuario {}, {}", username, roles.size());

            List<RoleRepresentation> rolesNuevos =  roles(perfil, getRealmResource());
            userResource.roles().realmLevel().remove(rolesNuevos);
            LOG.info("Perfil cambiado para {}", userResource.toRepresentation().getId());
        } catch (Exception ex){
            LOG.info("Error al cambiar perfil del {}", ex.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al bloquear");
        }
    }

    private List<RoleRepresentation> roles(String perfil, RealmResource realmResource){
        List<RoleRepresentation> roles = new ArrayList<>();
        if(perfil.equals("ADMINISTRADOR")){
            roles.add(realmResource.roles().get(ParamsManager.REALM_ADMIN).toRepresentation());
            roles.add(realmResource.roles().get(ParamsManager.REALM_USER).toRepresentation());
            return roles;
        }
        roles.add(realmResource.roles().get(ParamsManager.REALM_USER).toRepresentation());
        return roles;
    }

    private UserResource getUserResource(String username){
        UserRepresentation users = findByUsername(username);
        String id = users.getId();
        LOG.info("Usuario ID {}", id);
        Keycloak keycloak = config.keycloak();
        RealmResource realmResource = keycloak.realm(ParamsManager.REALM);
        UsersResource usersRessource = realmResource.users();

        UserResource userResource = usersRessource.get(id);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userResource.update(userRepresentation);
        return userResource;
    }

    private RealmResource getRealmResource(){
        return config.keycloak().realm(ParamsManager.REALM);
    }

}
