package pe.regioncusco.gob.simecr.modules.control.domain.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

public enum ProbabilidadEnum {
    BAJA(4, "BAJA"),
    MEDIA(6, "MEDIA"),
    ALTA(8, "ALTA"),
    MUY_ALTA(10, "MUY_ALTA");

    private final Integer value;
    private final String key;
    private static final ProbabilidadEnum[] VALUES = values();

    ProbabilidadEnum(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static ProbabilidadEnum valueOf(Integer statusCode) {
        ProbabilidadEnum status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static ProbabilidadEnum resolve(Integer statusCode) {
        ProbabilidadEnum[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ProbabilidadEnum status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
