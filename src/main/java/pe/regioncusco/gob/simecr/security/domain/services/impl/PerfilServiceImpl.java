package pe.regioncusco.gob.simecr.security.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.commons.MyValue;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.security.data.entities.PerfilEntity;
import pe.regioncusco.gob.simecr.security.data.respositorys.PerfilEntityRepository;
import pe.regioncusco.gob.simecr.security.domain.mappers.PerfilMapper;
import pe.regioncusco.gob.simecr.security.domain.models.Perfil;
import pe.regioncusco.gob.simecr.security.domain.services.PerfilService;

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
