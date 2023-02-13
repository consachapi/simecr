package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.persistences.MedidasRemediacionPersistencia;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.commons.DeficienciaCommon;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities.DeficienciaEntity;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities.MedidasRemediacionEntity;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.repositorys.MedidasRemediacionEntityRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MedidasRemediacionPersistenciaData implements MedidasRemediacionPersistencia {
    private static final Logger LOG = LoggerFactory.getLogger(MedidasRemediacionPersistenciaData.class);

    @Autowired private MedidasRemediacionEntityRepository medidasRemediacionEntityRepository;

    @Override
    public MedidasRemediacion save(MedidasRemediacion medidasRemediacion, String usuario, Integer estado) {
        DeficienciaEntity deficienciaEntity = DeficienciaCommon.toDeficienciaEntity(medidasRemediacion.getDeficiencia());
        MedidasRemediacionEntity medidasRemediacionEntity = new MedidasRemediacionEntity();
        medidasRemediacionEntity.setRemediacion(medidasRemediacion.getRemediacion());
        medidasRemediacionEntity.setDeficiencia(deficienciaEntity);
        medidasRemediacionEntity.setFechaRegistro(new Date());
        medidasRemediacionEntity.setUsuario(usuario);
        medidasRemediacionEntity.setEstado(estado);

        MedidasRemediacionEntity nuevo = medidasRemediacionEntityRepository.save(medidasRemediacionEntity);
        LOG.info("Medida Remediacion Entity creado {}", nuevo.getId());
        return nuevo.toMedidasRemediacion();
    }

    @Override
    public MedidasRemediacion update(MedidasRemediacion medidasRemediacion, Integer estado) {
        DeficienciaEntity deficienciaEntity = DeficienciaCommon.toDeficienciaEntity(medidasRemediacion.getDeficiencia());
        MedidasRemediacionEntity medidasRemediacionEntity = medidasRemediacionEntityRepository.findById(medidasRemediacion.getId()).get();
        medidasRemediacionEntity.setRemediacion(medidasRemediacion.getRemediacion());
        medidasRemediacionEntity.setDeficiencia(deficienciaEntity);
        medidasRemediacionEntity.setEstado(estado);

        MedidasRemediacionEntity update = medidasRemediacionEntityRepository.save(medidasRemediacionEntity);
        LOG.info("Medida Remediacion Entity actualizado {}", update.getId());
        return update.toMedidasRemediacion();
    }

    @Override
    public List<MedidasRemediacion> findAll() {
        List<MedidasRemediacionEntity> medidasRemediacionEntities = medidasRemediacionEntityRepository.findAll();
        return medidasRemediacionEntities.stream().map(medidasRemediacionEntity -> medidasRemediacionEntity.toMedidasRemediacion()).collect(Collectors.toList());
    }

    @Override
    public Optional<MedidasRemediacion> findById(Long id) {
        Optional<MedidasRemediacionEntity> optionalMedidasRemediacionEntity = medidasRemediacionEntityRepository.findById(id);
        return optionalMedidasRemediacionEntity.map(medidasRemediacionEntity -> medidasRemediacionEntity.toMedidasRemediacion());
    }

}
