package pe.regioncusco.gob.simecr.application.control.domain.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

public enum ImpactoEnum {
    BAJA(4, "BAJA"),
    MEDIA(6, "MEDIA"),
    ALTA(8, "ALTA"),
    MUY_ALTA(10, "MUY_ALTA");

    private final Integer value;
    private final String key;
    private static final ImpactoEnum[] VALUES = values();

    ImpactoEnum(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static ImpactoEnum valueOf(Integer statusCode) {
        ImpactoEnum status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static ImpactoEnum resolve(Integer statusCode) {
        ImpactoEnum[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ImpactoEnum status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }

}
