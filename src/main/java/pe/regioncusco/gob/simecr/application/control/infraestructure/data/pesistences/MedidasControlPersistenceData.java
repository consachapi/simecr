package pe.regioncusco.gob.simecr.application.control.infraestructure.data.pesistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.repositorys.MedidasControlEntityRepository;
import pe.regioncusco.gob.simecr.application.control.domain.presistences.MedidasControlPersistence;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.commons.RiesgoAdapterCommon;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.MedidasControlEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MedidasControlPersistenceData implements MedidasControlPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(MedidasControlPersistenceData.class);

    @Autowired private MedidasControlEntityRepository medidasControlEntityRepository;

    @Override
    public MedidasControl save(MedidasControl medidasControl, String usuario, Integer estado) {
        MedidasControlEntity medidasControlEntity = new MedidasControlEntity();
        BeanUtils.copyProperties(medidasControl, medidasControlEntity);
        medidasControlEntity.setRiesgo(RiesgoAdapterCommon.toRiesgoEntity(medidasControl.getRiesgo()));
        medidasControlEntity.setUsuario(usuario);
        medidasControlEntity.setFechaRegistro(new Date());
        medidasControlEntity.setEstado(estado);

        MedidasControlEntity nuevoEntity = medidasControlEntityRepository.save(medidasControlEntity);
        LOG.info("Medidad de Control Entity creado con id {}", medidasControlEntity.getId());
        return nuevoEntity.toMedidasControl();
    }

    @Override
    public List<MedidasControl> findAll() {
        List<MedidasControlEntity> medidasControlEntities = medidasControlEntityRepository.findAll();
        LOG.info("Medidas de Control encontrados {}", medidasControlEntities.size());
        return medidasControlEntities.stream().map(medidasControlEntity -> medidasControlEntity.toMedidasControl()).collect(Collectors.toList());
    }

    @Override
    public void delete(MedidasControl medidaControl, String usuario, Integer estado) {
        Optional<MedidasControlEntity> medidasControlEntity = medidasControlEntityRepository.findById(medidaControl.getId());
        MedidasControlEntity controlEntity = medidasControlEntity.get();
        controlEntity.setEstado(estado);
        controlEntity.setUsuario(usuario);
        medidasControlEntityRepository.save(controlEntity);
    }

    @Override
    public Optional<MedidasControl> findById(Long id) {
        Optional<MedidasControlEntity> optionalMedidasControlEntity = medidasControlEntityRepository.findById(id);
        return optionalMedidasControlEntity.map(medidasControlEntity -> medidasControlEntity.toMedidasControl());
    }
}
