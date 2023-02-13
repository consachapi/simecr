package pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Eje;
import pe.regioncusco.gob.simecr.application.parametros.domain.persistences.EjePersistence;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.EjeEntity;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.repositorys.EjeEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EjePersistenceData implements EjePersistence {
    private static final Logger LOG = LoggerFactory.getLogger(EjePersistenceData.class);

    @Autowired private EjeEntityRepository ejeEntityRepository;

    @Override
    public List<Eje> findAll() {
        List<EjeEntity> ejeEntities = ejeEntityRepository.findAll();
        LOG.info("Eje encontrados un total de {}", ejeEntities.size());
        return ejeEntities.stream().map(ejeEntity -> ejeEntity.toEje()).collect(Collectors.toList());
    }

    @Override
    public Optional<Eje> findById(Long id) {
        Optional<EjeEntity> optional = ejeEntityRepository.findById(id);
        return optional.map(ejeEntity -> ejeEntity.toEje());
    }

}
