package pe.regioncusco.gob.simecr.core.exceptions;

public class BadGatewayException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Gateway Exception";

    public BadGatewayException(String message){
        super(DESCRIPTION + ". " + message);
    }
}
