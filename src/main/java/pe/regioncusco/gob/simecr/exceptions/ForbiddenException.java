package pe.regioncusco.gob.simecr.exceptions;

public class ForbiddenException extends RuntimeException {
    private static final String DESCRIPTION = "Forbidden Exception";

    public ForbiddenException(String message){
        super(DESCRIPTION + ". " + message);
    }
}
