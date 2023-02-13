package pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.application.parametros.domain.persistences.OficinaPersistence;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.OficinaEntity;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.repositorys.OficinaEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OficinaPersistenceData implements OficinaPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(OficinaPersistenceData.class);

    @Autowired
    private OficinaEntityRepository oficinaEntityRepository;

    @Override
    public List<Oficina> findAll() {
        List<OficinaEntity> oficinaEntities = oficinaEntityRepository.findAll();
        LOG.info("Total de oficinas {}", oficinaEntities.size());
        return oficinaEntities.stream().map(oficinaEntity -> oficinaEntity.toOficina()).collect(Collectors.toList());
    }

    @Override
    public Optional<Oficina> findByAbreviatura(String abreviatura) {
        Optional<OficinaEntity> oficinaEntity = oficinaEntityRepository.findByAbreviatura(abreviatura);
        return oficinaEntity.map(oficina -> oficina.toOficina());
    }
}
