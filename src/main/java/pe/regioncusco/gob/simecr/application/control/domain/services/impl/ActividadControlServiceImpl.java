package pe.regioncusco.gob.simecr.modules.control.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.core.exceptions.BadRequestException;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.ActividadEstado;
import pe.regioncusco.gob.simecr.modules.control.domain.mappers.ActividadControlMapper;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ActividadControlBodyDto;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ActividadControlDto;
import pe.regioncusco.gob.simecr.modules.control.domain.presistences.ActividadControlPersistence;
import pe.regioncusco.gob.simecr.modules.control.domain.services.ActividadControlService;
import pe.regioncusco.gob.simecr.modules.control.domain.services.ControlVerificacionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActividadControlServiceImpl implements ActividadControlService {
    private static final Logger LOG = LoggerFactory.getLogger(ActividadControlServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private ActividadControlPersistence actividadControlPersistence;
    @Autowired private ControlVerificacionService controlVerificacionService;
    @Autowired private ActividadControlMapper actividadControlMapper;

    @Override
    public ActividadControlDto save(Long id, ActividadControlBodyDto actividad) {
        ControlVerificacion controlVerificacion = controlVerificacionService.findControlVerificacionById(id);

        ActividadControl actividadControl = new ActividadControl();
        actividadControl.setActividad(actividad.getActividad());
        actividadControl.setControlVerificacion(controlVerificacion);
        actividadControl.setFecha(actividad.getFecha());

        ActividadControl nuevo = actividadControlPersistence.save(actividadControl, accessToken.getUserId(), ActividadEstado.PENDIENTE.value());
        LOG.info("Nuevo actividad de medidad de control creado {}", nuevo.getId());
        return actividadControlMapper.toActividadControlDto(nuevo);
    }

    @Override
    public List<ActividadControlDto> findAllByControlVerificacion(Long id) {
        ControlVerificacion controlVerificacion = controlVerificacionService.findControlVerificacionById(id);
        List<ActividadControl> actividadControls = actividadControlPersistence.findAllByControlVerificacion(controlVerificacion);
        LOG.info("Actividad encontradas del Medio de Verificacion {}", id);
        return actividadControls.stream().map(actividadControl -> actividadControlMapper.toActividadControlDto(actividadControl)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        ActividadControl actividadControl = findById(id);
        if(actividadControl.getEstado() == ActividadEstado.COMPLETADO.value()){
            throw new BadRequestException("Sólo se puede eliminar en estado PENDIENTE");
        }
        actividadControlPersistence.delete(actividadControl);
    }

    @Override
    public ActividadControl findActividadControlById(Long id) {
        return findById(id);
    }

    @Override
    public ActividadControlDto finalize(Long id) {
        ActividadControl actividadControl = findById(id);
        ActividadControl update = actividadControlPersistence.finalize(actividadControl, ActividadEstado.COMPLETADO.value());
        return actividadControlMapper.toActividadControlDto(update);
    }

    private ActividadControl findById(Long id){
        Optional<ActividadControl> optionalActividadControl = actividadControlPersistence.findById(id);
        if(!optionalActividadControl.isPresent()){
            throw new NotFoundException("No existe Actividad del medio de verificación.");
        }
        return optionalActividadControl.get();
    }
}
