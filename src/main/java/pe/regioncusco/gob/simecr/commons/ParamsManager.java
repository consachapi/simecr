package pe.regioncusco.gob.simecr.commons;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class ParamsManager {

    public static final String SERVER_URL = "http://127.0.0.1:8080/auth";
    //public static final String SERVER_URL = "http://oauth.regioncusco.gob.pe/auth";
    public static final String REALM_MASTER = "master";
    public static final String REALM = "simecr";
    public static final String CLIENT_ID = "admin-cli";
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "213141";

    public static final String REALM_SUPER = "simecr-realm-super";
    public static final String REALM_ADMIN = "simecr-realm-admin";
    public static final String REALM_RESP = "simecr-realm-responsable";
    public static final String REALM_USER = "simecr-realm-user";

    public static final String ROLE_SUPER = "simecr_super";
    public static final String ROLE_ADMIN = "simecr_admin";
    public static final String ROLE_RESP = "simecr_responsable";
    public static final String ROLE_USER = "simecr_user";

    public static final Path PATH_DOCUMENTOS_CONTROL = Paths.get("control");
    public static final Path PATH_DOCUMENTOS_REMEMDIACION = Paths.get("remediacion");

    public static final String BUCKET = "simecr";
    public static final String FOLDER_CONTROL = "control/";
    public static final String FOLDER_REMEDIACION = "remediacion/";

    private static ParamsManager paramsManager;

    private ParamsManager(){

    }

    public static ParamsManager getInstance(){
        if(paramsManager == null){
            return new ParamsManager();
        }
        return paramsManager;
    }

}
