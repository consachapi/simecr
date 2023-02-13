package pe.regioncusco.gob.simecr.security.infraestructures.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;
import pe.regioncusco.gob.simecr.security.domains.dtos.UsuarioDto;
import pe.regioncusco.gob.simecr.security.domains.models.Usuario;
import pe.regioncusco.gob.simecr.security.applications.services.UsuarioService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Map;

@Rest
@RequestMapping(UsuarioController.USUARIO)
public class UsuarioController {
    public static final String USUARIO = "/v1/usuario";
    private static final String LOGOUT = "/logout/{session}";
    private static final String ROLES = "/roles";
    private static final String CREAR = "/crear";
    private static final String BUSCAR = "/buscar/{username}";
    private static final String RESET = "/reset/{id}";

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(ROLES)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_USER)
    public ResponseEntity<Usuario> ver(){
        return new ResponseEntity<>(usuarioService.getPerfilUsuario(), HttpStatus.OK);
    }

    @DeleteMapping(LOGOUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsSecurity.ROLE_USER)
    public void closeSession(@PathVariable String session){
        usuarioService.logout(session);
    }

    @PostMapping(CREAR)
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<?> save(@Valid @RequestBody UsuarioDto usuario){
        return new ResponseEntity<>(usuarioService.create(usuario), HttpStatus.CREATED);
    }

    @GetMapping(BUSCAR)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<?> findByUsername(@PathVariable String username){
        return new ResponseEntity<>(usuarioService.findByUsername(username), HttpStatus.OK);
    }

    @PutMapping(RESET)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsSecurity.ROLE_ADMIN)
    public ResponseEntity<?> save(@PathVariable String id, @RequestBody Map<String, Object> password){
        return new ResponseEntity<>(usuarioService.reset(id, password), HttpStatus.OK);
    }

}
