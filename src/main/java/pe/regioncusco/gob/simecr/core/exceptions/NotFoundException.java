package pe.regioncusco.gob.simecr.core.exceptions;

public class NotFoundException extends RuntimeException {
    private static final String DESCRPTION = "Not Found";

    public NotFoundException(String message){
        super(DESCRPTION + ". " + message);
    }
}
