package pe.regioncusco.gob.simecr.application.remediacion.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.application.remediacion.domain.persistences.ActividadRemediacionPersistence;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.core.exceptions.BadRequestException;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.control.domain.enums.ActividadEstado;
import pe.regioncusco.gob.simecr.application.control.domain.services.impl.ActividadControlServiceImpl;
import pe.regioncusco.gob.simecr.application.remediacion.domain.mappers.ActividadRemediacionMapper;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.RemediacionVerificacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.ActividadRemediacionBodyDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.ActividadRemediacionDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.ActividadRemediacionService;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.RemediacionVerificacionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActividadRemediacionServiceImpl implements ActividadRemediacionService {
    private static final Logger LOG = LoggerFactory.getLogger(ActividadControlServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private ActividadRemediacionPersistence actividadRemediacionPersistence;
    @Autowired private RemediacionVerificacionService remediacionVerificacionService;
    @Autowired private ActividadRemediacionMapper actividadRemediacionMapper;

    @Override
    public ActividadRemediacionDto save(Long id, ActividadRemediacionBodyDto actividad) {
        RemediacionVerificacion remediacionVerificacion = remediacionVerificacionService.findRemediacionVerificacionById(id);

        ActividadRemediacion actividadRemediacion = new ActividadRemediacion();
        actividadRemediacion.setActividad(actividad.getActividad());
        actividadRemediacion.setRemediacionVerificacion(remediacionVerificacion);
        actividadRemediacion.setFecha(actividad.getFecha());

        ActividadRemediacion nuevo = actividadRemediacionPersistence.save(actividadRemediacion, accessToken.getUserId(), ActividadEstado.PENDIENTE.value());
        LOG.info("Nuevo actividad de medidad de control creado {}", nuevo.getId());
        return actividadRemediacionMapper.toActividadRemediacionDto(nuevo);
    }

    @Override
    public List<ActividadRemediacionDto> findAllByRemediacionVerificacion(Long id) {
        RemediacionVerificacion remediacionVerificacion = remediacionVerificacionService.findRemediacionVerificacionById(id);
        List<ActividadRemediacion> actividadRemediacions = actividadRemediacionPersistence.findAllByRemediacionVerificacion(remediacionVerificacion);
        LOG.info("Actividad encontradas del Medio de Verificacion {}", id);
        return actividadRemediacions.stream().map(actividadRemediacion -> actividadRemediacionMapper.toActividadRemediacionDto(actividadRemediacion)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        ActividadRemediacion actividadRemediacion = findById(id);
        if(actividadRemediacion.getEstado() == ActividadEstado.COMPLETADO.value()){
            LOG.error("No existe actividad {} esta es estado COMPLETADO", id);
            throw new BadRequestException("Sólo se puede eliminar en estado PENDIENTE");
        }
        actividadRemediacionPersistence.delete(actividadRemediacion);
    }

    @Override
    public ActividadRemediacion findActividadRemediacionById(Long id) {
        return findById(id);
    }

    @Override
    public ActividadRemediacionDto finalize(Long id) {
        ActividadRemediacion actividadRemediacion = findById(id);
        return actividadRemediacionMapper.toActividadRemediacionDto(actividadRemediacionPersistence.finalize(actividadRemediacion, ActividadEstado.COMPLETADO.value()));
    }

    private ActividadRemediacion findById(Long id){
        Optional<ActividadRemediacion> actividadRemediacion = actividadRemediacionPersistence.findById(id);
        if(!actividadRemediacion.isPresent()){
            LOG.error("No existe actividad {} para el medio de verificacion", id);
            throw new NotFoundException("No existe Actividad del medio de verificación.");
        }
        return actividadRemediacion.get();
    }

}
