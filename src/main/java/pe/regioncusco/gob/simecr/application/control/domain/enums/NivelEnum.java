package pe.regioncusco.gob.simecr.application.control.domain.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

public enum NivelEnum {
    RB(1, "Riesgo Bajo"),
    RM(2, "Riesgo Medio"),
    RA(3, "Riesgo Alto"),
    RMA(4, "Riesgo Muy Alto");

    private final Integer value;
    private final String key;
    private static final NivelEnum[] VALUES = values();

    NivelEnum(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static NivelEnum valueOf(Integer statusCode) {
        NivelEnum status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static NivelEnum resolve(Integer statusCode) {
        NivelEnum[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            NivelEnum status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
