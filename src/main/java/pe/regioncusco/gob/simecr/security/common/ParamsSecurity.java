package pe.regioncusco.gob.simecr.security.common;

public final class ParamsSecurity {
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

    public static final String SUPER = "SUPER";
    public static final String ADMIN = "ADMIN";
    public static final String RESP = "RESP";
    public static final String USER = "USER";

    public static final String SUPER_USER = "admin";
    public static final String SUPER_CARGO = "Administrador del Sistema";
    public static final String UNIDAD_EJECUTORA_DEFAULT = "000";

    private static ParamsSecurity paramsSecurity;

    private ParamsSecurity(){
    }

    public static ParamsSecurity getInstance(){
        if(paramsSecurity == null){
            return new ParamsSecurity();
        }
        return paramsSecurity;
    }

}
