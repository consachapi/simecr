package pe.regioncusco.gob.simecr.security.infraestructures.feign.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class PersonaServiceFallbackFactory implements FallbackFactory<PersonaServiceFallback> {

    @Override
    public PersonaServiceFallback create(Throwable cause) {
        return new PersonaServiceFallback();
    }
}
