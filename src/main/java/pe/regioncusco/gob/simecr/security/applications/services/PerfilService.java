package pe.regioncusco.gob.simecr.security.domain.services;

import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.security.domains.models.Perfil;

import java.util.List;

public interface PerfilService {
    List<MyValue> findAllSelection();
    Perfil findPerfilById(Long id);
}
