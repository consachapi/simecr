package pe.regioncusco.gob.simecr.security.domains.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

public enum PerfilEnum {
    ADMINISTRADOR(1, "ADMINISTRADOR"),
    USUARIO(2, "USUARIO");

    private final Integer value;
    private final String key;
    private static final PerfilEnum[] VALUES = values();

    PerfilEnum(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static PerfilEnum valueOf(Integer statusCode) {
        PerfilEnum status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static PerfilEnum resolve(Integer statusCode) {
        PerfilEnum[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            PerfilEnum status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
