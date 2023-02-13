package pe.regioncusco.gob.simecr.security.config;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

import java.security.Principal;
import java.util.Set;

@Component
public class AccessTokenImpl {
    private AccessToken accessToken;
    private Principal principal;

    public String getUserId(){
        accessToken();
        if(accessToken == null){
            throw new NotFoundException("Verifique su sesion.");
        }
        return accessToken.getPreferredUsername();
    }

    public String getUserName(){
        if(accessToken == null){
            throw new NotFoundException("Verifique su sesion.");
        }
        return accessToken.getName();
    }

    public Set<String> getUserRole(){
        accessToken();
        if(accessToken == null){
            throw new NotFoundException("Verifique su sesion.");
        }

        if(accessToken.getRealmAccess() == null){
            throw new NotFoundException("Verifique su sesion.");
        }
        return accessToken.getRealmAccess().getRoles();
    }

    public boolean isAnonymous(){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")){
            return true;
        }
        return false;
    }

    public String getEjecCodigo(){
        accessToken();
        if(accessToken.getOtherClaims().isEmpty()){
            return "000";
        }
        return (String) accessToken.getOtherClaims().get("ejecutora");
    }

    public String getPrincipal(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    private void accessToken(){
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        this.accessToken = authentication.getAccount().getKeycloakSecurityContext().getToken();
        this.principal = (Principal) authentication.getPrincipal();
    }

}
