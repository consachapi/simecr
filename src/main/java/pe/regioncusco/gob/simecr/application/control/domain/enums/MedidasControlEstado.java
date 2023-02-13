package pe.regioncusco.gob.simecr.modules.control.domain.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

public enum MedidasControlEstado {
    ANULADO(0, "ANULADO"),
    ACTIVO(1, "ACTIVO"),
    COMPLETADO(2, "COMPLETADO");

    private final Integer value;
    private final String key;
    private static final MedidasControlEstado[] VALUES = values();

    MedidasControlEstado(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static MedidasControlEstado valueOf(Integer statusCode) {
        MedidasControlEstado status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static MedidasControlEstado resolve(Integer statusCode) {
        MedidasControlEstado[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            MedidasControlEstado status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
