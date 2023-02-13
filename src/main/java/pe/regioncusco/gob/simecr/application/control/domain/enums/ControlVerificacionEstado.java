package pe.regioncusco.gob.simecr.modules.control.domain.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

public enum ControlVerificacionEstado {
    PENDIENTE(1, "PENDIENTE"),
    EN_PROCESO(2, "EN_PROCESO"),
    COMPLETADO(3, "COMPLETADO"),
    INCOMPLETO(4, "INCOMPLETO"),
    ANULADO(5, "ANULADO");

    private final Integer value;
    private final String key;
    private static final ControlVerificacionEstado[] VALUES = values();

    ControlVerificacionEstado(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static ControlVerificacionEstado valueOf(Integer statusCode) {
        ControlVerificacionEstado status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static ControlVerificacionEstado resolve(Integer statusCode) {
        ControlVerificacionEstado[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ControlVerificacionEstado status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }

}
