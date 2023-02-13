package pe.regioncusco.gob.simecr.security.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.regioncusco.gob.simecr.security.common.ParamsSecurity;

@Component
public class UtilConfig {
    @Value("${keycloak.auth-server-url}")
    private String authServer;

    public Keycloak keycloak(){
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(authServer)
                .realm(ParamsSecurity.REALM_MASTER)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(ParamsSecurity.CLIENT_ID)
                .username(ParamsSecurity.USERNAME)
                .password(ParamsSecurity.PASSWORD)
                .build();
        return keycloak;
    }
}
