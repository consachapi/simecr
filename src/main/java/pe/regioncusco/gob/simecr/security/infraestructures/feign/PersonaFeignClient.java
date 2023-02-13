package pe.regioncusco.gob.simecr.security.infraestructures.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pe.regioncusco.gob.simecr.security.infraestructures.feign.config.MyFeignClientConfiguration;
import pe.regioncusco.gob.simecr.security.infraestructures.feign.fallback.PersonaServiceFallbackFactory;
import pe.regioncusco.gob.simecr.security.infraestructures.feign.models.PersonaTpl;

@FeignClient(value = "ms-fvirtual", url = "${service.persona.url}", configuration = MyFeignClientConfiguration.class, fallbackFactory = PersonaServiceFallbackFactory.class)
public interface PersonaFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/usuario/personal/consultar/{ndocumento}")
    PersonaTpl getPersona(@PathVariable("ndocumento") String ndocumento);
}
