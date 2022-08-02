package pe.regioncusco.gob.simecr.security.restfull.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class PersonaServiceFallbackFactory implements FallbackFactory<PersonaServiceFallback> {

    @Override
    public PersonaServiceFallback create(Throwable cause) {
        return new PersonaServiceFallback();
    }
}
