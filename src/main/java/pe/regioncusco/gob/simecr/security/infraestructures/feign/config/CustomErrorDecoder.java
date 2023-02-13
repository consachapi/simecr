package pe.regioncusco.gob.simecr.security.restfull.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.regioncusco.gob.simecr.exceptions.BadRequestException;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;

public class CustomErrorDecoder implements ErrorDecoder {
    private static final Logger LOG = LoggerFactory.getLogger(CustomErrorDecoder.class);

    @Override
    public Exception decode(String s, Response response) {
        LOG.error(response.body().toString());
        switch (response.status()){
            case 400:
                return new BadRequestException("Ocurrio un error al realizar la consulta");
            case 404:
                return new NotFoundException("El item o el recurso no existe.");
            default:
                return new BadRequestException("Ocurrio un error al realizar la busqueda");
        }
    }
}
