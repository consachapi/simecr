package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.commons.ControlVerificacionAdapterCommon;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ActividadControlEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ControlVerificacionEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.pesistences.ActividadControlPersistenceData;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.repositorys.ActividadControlEntityRepository;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.RemediacionVerificacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.persistences.ActividadRemediacionPersistence;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.commons.RemediacionVerificacionAdapterCommon;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.ActividadRemediacionEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.RemediacionVerificacionEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.repositorys.ActividadRemediacionEntityRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ActividadRemediacionPersistenceData implements ActividadRemediacionPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(ActividadControlPersistenceData.class);

    @Autowired private ActividadRemediacionEntityRepository actividadRemediacionEntityRepository;

    @Override
    public ActividadRemediacion save(ActividadRemediacion actividadRemediacion, String usuario, Integer estado) {
        LOG.info("Creando nuevo actividad para el medio de verificacion");
        RemediacionVerificacionEntity remediacionVerificacionEntity = RemediacionVerificacionAdapterCommon.toRemediacionVerificacionEntity(actividadRemediacion.getRemediacionVerificacion());
        ActividadRemediacionEntity controlEntity = new ActividadRemediacionEntity();
        BeanUtils.copyProperties(actividadRemediacion, controlEntity);
        controlEntity.setRemediacionVerificacion(remediacionVerificacionEntity);
        controlEntity.setUsuario(usuario);
        controlEntity.setFechaRegistro(new Date());
        controlEntity.setEstado(estado);

        ActividadRemediacionEntity nuevo = actividadRemediacionEntityRepository.save(controlEntity);
        return nuevo.toActividadRemediacion();
    }

    @Override
    public List<ActividadRemediacion> findAllByRemediacionVerificacion(RemediacionVerificacion remediacionVerificacion) {
        RemediacionVerificacionEntity remediacionVerificacionEntity = RemediacionVerificacionAdapterCommon.toRemediacionVerificacionEntity(remediacionVerificacion);
        List<ActividadRemediacionEntity> actividadRemediacionEntities = actividadRemediacionEntityRepository.findAllByRemediacionVerificacionOrderById(remediacionVerificacionEntity);
        return actividadRemediacionEntities.stream().map(actividadRemediacionEntity -> actividadRemediacionEntity.toActividadRemediacion()).collect(Collectors.toList());
    }

    @Override
    public void delete(ActividadRemediacion actividadRemediacion) {
        ActividadRemediacionEntity actividadRemediacionEntity = actividadRemediacionEntityRepository.findById(actividadRemediacion.getId()).get();
        actividadRemediacionEntityRepository.delete(actividadRemediacionEntity);
    }

    @Override
    public Optional<ActividadRemediacion> findById(Long id) {
        Optional<ActividadRemediacionEntity> optionalActividadRemediacionEntity = actividadRemediacionEntityRepository.findById(id);
        return optionalActividadRemediacionEntity.map(actividadRemediacionEntity -> actividadRemediacionEntity.toActividadRemediacion());
    }

    @Override
    public ActividadRemediacion finalize(ActividadRemediacion actividadRemediacion, Integer estado) {
        ActividadRemediacionEntity actividadRemediacionEntity = actividadRemediacionEntityRepository.findById(actividadRemediacion.getId()).get();
        actividadRemediacionEntity.setEstado(estado);

        ActividadRemediacionEntity update = actividadRemediacionEntityRepository.save(actividadRemediacionEntity);
        return update.toActividadRemediacion();
    }
}
