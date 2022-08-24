package pe.regioncusco.gob.simecr.modules.remediacion.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.ControlVerificacionEstado;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.modules.control.domain.services.impl.ControlVerificacionServiceImpl;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.enums.RemediacionVerificacionEstado;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.mappers.RemediacionVerificacionMapper;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.RemediacionVerificacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.RemediacionVerificacionBodyDto;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.RemediacionVerificacionDto;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.persistences.RemediacionVerificacionPersistence;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.services.MedidasRemediacionService;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.services.RemediacionVerificacionService;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;
import pe.regioncusco.gob.simecr.security.domain.services.PersonaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RemediacionVerificacionServiceImpl implements RemediacionVerificacionService {
    private static final Logger LOG = LoggerFactory.getLogger(ControlVerificacionServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private MedidasRemediacionService medidasRemediacionService;
    @Autowired private RemediacionVerificacionPersistence remediacionVerificacionPersistence;
    @Autowired private RemediacionVerificacionMapper remediacionVerificacionMapper;
    @Autowired private PersonaService personaService;

    @Override
    public RemediacionVerificacionDto save(RemediacionVerificacionBodyDto remediacionVerificacionBodyDto) {
        MedidasRemediacion medidasRemediacion = medidasRemediacionService.findMedidasRemediacionById(remediacionVerificacionBodyDto.getMedidasRemediacion());
        RemediacionVerificacion remediacionVerificacion = new RemediacionVerificacion();
        BeanUtils.copyProperties(remediacionVerificacionBodyDto, remediacionVerificacion);
        remediacionVerificacion.setMedidasRemediacion(medidasRemediacion);

        RemediacionVerificacion nuevoVeficacion = remediacionVerificacionPersistence.save(remediacionVerificacion, accessToken.getUserId(), RemediacionVerificacionEstado.PENDIENTE.value());
        LOG.info("Nuevo Medio de Verificacion para la remediacion {}, creado", nuevoVeficacion.getId());
        return remediacionVerificacionMapper.toRemediacionVerificacionDto(nuevoVeficacion);
    }

    @Override
    public List<RemediacionVerificacionDto> findAll() {
        return null;
    }

    @Override
    public List<RemediacionVerificacion> findAllRemediacionVerificacion() {
        return remediacionVerificacionPersistence.findAll();
    }

    @Override
    public List<RemediacionVerificacionDto> findAllByMedidaRemediacion(Long medidadRemediacion) {
        MedidasRemediacion medidasRemediacion = medidasRemediacionService.findMedidasRemediacionById(medidadRemediacion);
        List<RemediacionVerificacion> remediacionVerificacions = remediacionVerificacionPersistence.findAllByMedidasRemediacion(medidasRemediacion);
        return remediacionVerificacions.stream().map(remediacionVerificacion -> remediacionVerificacionMapper.toRemediacionVerificacionDto(remediacionVerificacion)).collect(Collectors.toList());
    }

    @Override
    public List<Oficina> setResponsableOficina(Long controlVerificacion, List<Oficina> oficinas) {
        RemediacionVerificacion remediacionVerificacion = findById(controlVerificacion);
        remediacionVerificacion.setOficinas(oficinas);
        RemediacionVerificacion update = remediacionVerificacionPersistence.updateOficina(remediacionVerificacion);
        return update.getOficinas();
    }

    @Override
    public RemediacionVerificacion findRemediacionVerificacionById(Long id) {
        return findById(id);
    }

    @Override
    public RemediacionVerificacionDto find(Long id) {
        return remediacionVerificacionMapper.toRemediacionVerificacionDto(findById(id));
    }

    @Override
    public List<RemediacionVerificacionDto> findAllByUsuario() {
        Persona persona = personaService.findById(accessToken.getUserId());
        if(persona.getPerfil().getDescripcion().equals("ADMINISTRADOR")){
            List<RemediacionVerificacion> remediacionVerificacions = remediacionVerificacionPersistence.findAll();
            return remediacionVerificacions.stream().map(remediacionVerificacion -> remediacionVerificacionMapper.toRemediacionVerificacionDto(remediacionVerificacion)).collect(Collectors.toList());
        }
        List<Oficina> oficinas = new ArrayList<>();
        oficinas.add(persona.getArea());
        List<RemediacionVerificacion> remediacionVerificacions = remediacionVerificacionPersistence.findAllByOficinas(oficinas);
        return remediacionVerificacions.stream().map(remediacionVerificacion -> remediacionVerificacionMapper.toRemediacionVerificacionDto(remediacionVerificacion)).collect(Collectors.toList());
    }

    @Override
    public RemediacionVerificacionDto cambiarEstado(Long id) {
        RemediacionVerificacion remediacionVerificacion = findById(id);
        if (remediacionVerificacion.getEstado() == RemediacionVerificacionEstado.PENDIENTE.value()) {
            RemediacionVerificacion remediacionVerificacion1 = this.remediacionVerificacionPersistence.changeEstado(remediacionVerificacion, RemediacionVerificacionEstado.COMPLETADO.value(), this.accessToken.getUserId());
            return this.remediacionVerificacionMapper.toRemediacionVerificacionDto(remediacionVerificacion1);
        }
        RemediacionVerificacion update = this.remediacionVerificacionPersistence.changeEstado(remediacionVerificacion, RemediacionVerificacionEstado.PENDIENTE.value(), this.accessToken.getUserId());
        return this.remediacionVerificacionMapper.toRemediacionVerificacionDto(update);
    }

    private RemediacionVerificacion findById(Long id){
        Optional<RemediacionVerificacion> optional = remediacionVerificacionPersistence.findById(id);
        if(!optional.isPresent()){
            LOG.error("No existe medio de verificacion con id {}", id);
            throw new NotFoundException("No existe medio de verificacion");
        }
        return optional.get();
    }
}
