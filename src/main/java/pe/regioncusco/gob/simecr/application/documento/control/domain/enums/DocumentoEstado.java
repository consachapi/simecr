package pe.regioncusco.gob.simecr.application.documento.control.domain.enums;

import org.springframework.lang.Nullable;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;

public enum DocumentoEstado {
    CARGADO(1, "CARGADO"),
    FIRMADO(2, "FIRMADO"),
    OBSERVADO(2, "OBSERVADO");

    private final Integer value;
    private final String key;
    private static final DocumentoEstado[] VALUES = values();

    DocumentoEstado(Integer value, String key) {
        this.value = value;
        this.key = key;
    }

    public Integer value(){
        return this.value;
    }

    public String key(){
        return this.key;
    }

    public static DocumentoEstado valueOf(Integer statusCode) {
        DocumentoEstado status = resolve(statusCode);
        if (status == null) {
            throw new NotFoundException("No matching constant for [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static DocumentoEstado resolve(Integer statusCode) {
        DocumentoEstado[] var1 = VALUES;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            DocumentoEstado status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }
}
