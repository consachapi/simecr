package pe.regioncusco.gob.simecr.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.regioncusco.gob.simecr.commons.ParamsManager;

@Component
public class UtilConfig {
    @Value("${keycloak.auth-server-url}")
    private String authServer;

    public Keycloak keycloak(){
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(authServer)
                .realm(ParamsManager.REALM_MASTER)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(ParamsManager.CLIENT_ID)
                .username(ParamsManager.USERNAME)
                .password(ParamsManager.PASSWORD)
                .build();
        return keycloak;
    }
}
