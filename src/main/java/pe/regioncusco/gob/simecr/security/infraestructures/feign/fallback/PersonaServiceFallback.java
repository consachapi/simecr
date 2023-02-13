package pe.regioncusco.gob.simecr.security.infraestructures.feign.fallback;

import pe.regioncusco.gob.simecr.security.infraestructures.feign.PersonaFeignClient;
import pe.regioncusco.gob.simecr.security.infraestructures.feign.models.PersonaData;
import pe.regioncusco.gob.simecr.security.infraestructures.feign.models.PersonaTpl;

import java.util.ArrayList;
import java.util.List;

public class PersonaServiceFallback implements PersonaFeignClient {
    @Override
    public PersonaTpl getPersona(String ndocumento) {
        PersonaData personaData = new PersonaData();
        personaData.setNumeroDocumento("00000000");
        personaData.setApellidos("PEREZ");
        personaData.setNombres("JUAN");
        personaData.setOficinaAbreviatura("SGMTI");
        personaData.setOficina("OFICINA FUNCIONAL DE INFORMATICA");
        personaData.setArea("SUBGERENCIA DE MODERNIZACION Y TECNOLOGIAS DE LA INFORMACION");
        personaData.setEmail("juan@regioncusco.gob.pe");
        personaData.setTelefono("084224455");
        personaData.setDireccion("WANCHAQ");

        List<PersonaData> data = new ArrayList<>();
        data.add(personaData);

        PersonaTpl personaTpl = new PersonaTpl();
        personaTpl.setSuccess(false);
        personaTpl.setMsg("Ocurrio un error en el lado del proveedor");
        personaTpl.setData(data);
        personaTpl.setTotal(1);
        return personaTpl;
    }
}
