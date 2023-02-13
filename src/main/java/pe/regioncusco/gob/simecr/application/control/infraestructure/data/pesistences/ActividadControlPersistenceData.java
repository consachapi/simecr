package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.pesistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.control.domain.presistences.ActividadControlPersistence;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.commons.ControlVerificacionAdapterCommon;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ActividadControlEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ControlVerificacionEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.repositorys.ActividadControlEntityRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ActividadControlPersistenceData implements ActividadControlPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(ActividadControlPersistenceData.class);
    @Autowired private ActividadControlEntityRepository actividadControlEntityRepository;

    @Override
    public ActividadControl save(ActividadControl actividadControl, String usuario, Integer estado) {
        LOG.info("Creando nuevo actividad para el medio de verificacion");
        ControlVerificacionEntity controlVerificacionEntity = ControlVerificacionAdapterCommon.toControlVerificacionEntity(actividadControl.getControlVerificacion());
        ActividadControlEntity controlEntity = new ActividadControlEntity();
        BeanUtils.copyProperties(actividadControl, controlEntity);
        controlEntity.setControlVerificacion(controlVerificacionEntity);
        controlEntity.setUsuario(usuario);
        controlEntity.setFechaRegistro(new Date());
        controlEntity.setEstado(estado);

        ActividadControlEntity nuevo = actividadControlEntityRepository.save(controlEntity);
        return nuevo.toActividadControl();
    }

    @Override
    public List<ActividadControl> findAllByControlVerificacion(ControlVerificacion controlVerificacion) {
        ControlVerificacionEntity controlVerificacionEntity = ControlVerificacionAdapterCommon.toControlVerificacionEntity(controlVerificacion);
        List<ActividadControlEntity> actividadControlEntities = actividadControlEntityRepository.findAllByControlVerificacionOrderByFecha(controlVerificacionEntity);
        return actividadControlEntities.stream().map(actividadControlEntity -> actividadControlEntity.toActividadControl()).collect(Collectors.toList());
    }

    @Override
    public void delete(ActividadControl actividadControl) {
        ActividadControlEntity controlEntity = actividadControlEntityRepository.findById(actividadControl.getId()).get();
        actividadControlEntityRepository.delete(controlEntity);
    }

    @Override
    public Optional<ActividadControl> findById(Long id) {
        Optional<ActividadControlEntity> optionalActividadControlEntity = actividadControlEntityRepository.findById(id);
        return optionalActividadControlEntity.map(actividadControlEntity -> actividadControlEntity.toActividadControl());
    }

    @Override
    public ActividadControl finalize(ActividadControl actividadControl, Integer estado) {
        ActividadControlEntity actividadControlEntity = actividadControlEntityRepository.findById(actividadControl.getId()).get();
        actividadControlEntity.setEstado(estado);
        ActividadControlEntity update = actividadControlEntityRepository.save(actividadControlEntity);
        return update.toActividadControl();
    }
}
