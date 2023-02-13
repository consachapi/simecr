package pe.regioncusco.gob.simecr.application.control.domain.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

public enum RiesgoEstado {
    ANULADO(0, "ANULADO"),
    ACTIVO(1, "ACTIVO"),
    COMPLETADO(2, "COMPLETADO");

    private final Integer value;
    private final String key;
    private static final RiesgoEstado[] VALUES = values();

    RiesgoEstado(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static RiesgoEstado valueOf(Integer statusCode) {
        RiesgoEstado status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static RiesgoEstado resolve(Integer statusCode) {
        RiesgoEstado[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            RiesgoEstado status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
