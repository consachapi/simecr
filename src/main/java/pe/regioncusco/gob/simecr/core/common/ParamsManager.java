package pe.regioncusco.gob.simecr.core.common;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class ParamsManager {
    public static final Path PATH_DOCUMENTOS_CONTROL = Paths.get("control");
    public static final Path PATH_DOCUMENTOS_REMEMDIACION = Paths.get("remediacion");

    public static final String BUCKET = "simecr";
    public static final String FOLDER_CONTROL = "control/";
    public static final String FOLDER_REMEDIACION = "remediacion/";

    private static ParamsManager paramsManager;

    private ParamsManager(){

    }

    public static ParamsManager getInstance(){
        if(paramsManager == null){
            return new ParamsManager();
        }
        return paramsManager;
    }

}
