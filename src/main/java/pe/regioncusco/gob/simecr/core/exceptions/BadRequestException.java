package pe.regioncusco.gob.simecr.core.exceptions;

public class BadRequestException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Request Exception";

    public BadRequestException(String message) {
        super(DESCRIPTION + ". " + message);
    }

}
