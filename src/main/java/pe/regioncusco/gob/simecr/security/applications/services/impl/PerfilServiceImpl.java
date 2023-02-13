package pe.regioncusco.gob.simecr.security.applications.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PerfilEntity;
import pe.regioncusco.gob.simecr.security.infraestructures.database.respositorys.PerfilEntityRepository;
import pe.regioncusco.gob.simecr.security.domains.mappers.PerfilMapper;
import pe.regioncusco.gob.simecr.security.domains.models.Perfil;
import pe.regioncusco.gob.simecr.security.applications.services.PerfilService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerfilServiceImpl implements PerfilService {
    private static final Logger LOG = LoggerFactory.getLogger(PersonaServiceImpl.class);

    @Autowired private PerfilEntityRepository perfilEntityRepository;
    @Autowired private PerfilMapper perfilMapper;

    @Override
    public List<MyValue> findAllSelection() {
        List<PerfilEntity> perfilEntities = perfilEntityRepository.findAll();
        LOG.info("Perfiles encontrados {}", perfilEntities.size());
        return perfilEntities.stream().map(perfilEntity -> perfilMapper.toMyValue(perfilEntity.toPerfil())).collect(Collectors.toList());
    }

    @Override
    public Perfil findPerfilById(Long id) {
        return findById(id);
    }

    private Perfil findById(Long id){
        Optional<Perfil> optionalPerfil = perfilEntityRepository.findById(id).map(perfilEntity -> perfilEntity.toPerfil());
        return optionalPerfil.orElseThrow(() -> new NotFoundException("No existe perfil"));
    }
}
