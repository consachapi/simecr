package pe.regioncusco.gob.simecr.core.exceptions;

public class ConflictException extends RuntimeException {
    private static final String DESCRIPTION = "Conflicto";//Conflit Exception

    public ConflictException(String message){
        super(DESCRIPTION + ". " + message);
    }
}
