package pe.regioncusco.gob.simecr.modules.control.domain.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

public enum ActividadEstado {
    PENDIENTE(1, "PENDIENTE"),
    COMPLETADO(2, "COMPLETADO"),
    ANULADO(3, "ANULADO");

    private final Integer value;
    private final String key;
    private static final ActividadEstado[] VALUES = values();

    ActividadEstado(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static ActividadEstado valueOf(Integer statusCode) {
        ActividadEstado status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static ActividadEstado resolve(Integer statusCode) {
        ActividadEstado[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ActividadEstado status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
