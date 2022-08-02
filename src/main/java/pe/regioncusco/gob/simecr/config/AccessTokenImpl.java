package pe.regioncusco.gob.simecr.config;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Set;

@Component
public class AccessTokenImpl {
    private AccessToken accessToken;
    private Principal principal;

    public String getUserId(){
        accessToken();
        if(accessToken == null){
            return "";
        }
        return accessToken.getPreferredUsername();
    }

    public String getUserName(){
        if(accessToken == null){
            return "";
        }
        return accessToken.getName();
    }

    public Set<String> getUserRole(){
        accessToken();
        if(accessToken == null){
            return null;
        }

        if(accessToken.getRealmAccess() == null){
            return null;
        }
        return accessToken.getRealmAccess().getRoles();
    }

    public boolean isAnonymous(){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")){
            return true;
        }
        return false;
    }

    private void accessToken(){
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        this.accessToken = authentication.getAccount().getKeycloakSecurityContext().getToken();
        this.principal = (Principal) authentication.getPrincipal();
    }

    public String getPrincipal(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
