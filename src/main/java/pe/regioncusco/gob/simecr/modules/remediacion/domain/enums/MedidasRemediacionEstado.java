package pe.regioncusco.gob.simecr.modules.remediacion.domain.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;

public enum MedidasRemediacionEstado {
    ANULADO(0, "ANULADO"),
    ACTIVO(1, "ACTIVO"),
    COMPLETADO(2, "COMPLETADO");

    private final Integer value;
    private final String key;
    private static final MedidasRemediacionEstado[] VALUES = values();

    MedidasRemediacionEstado(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static MedidasRemediacionEstado valueOf(Integer statusCode) {
        MedidasRemediacionEstado status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static MedidasRemediacionEstado resolve(Integer statusCode) {
        MedidasRemediacionEstado[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            MedidasRemediacionEstado status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }

}
