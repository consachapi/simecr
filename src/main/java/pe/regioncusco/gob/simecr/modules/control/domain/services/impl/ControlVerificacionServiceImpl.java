package pe.regioncusco.gob.simecr.modules.control.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.ControlVerificacionEstado;
import pe.regioncusco.gob.simecr.modules.control.domain.mappers.ControlVerificacionMapper;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ControlVerificacionBodyDto;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ControlVerificacionDto;
import pe.regioncusco.gob.simecr.modules.control.domain.presistences.ControlVerificacionPersistence;
import pe.regioncusco.gob.simecr.modules.control.domain.services.ControlVerificacionService;
import pe.regioncusco.gob.simecr.modules.control.domain.services.MedidasControlService;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;
import pe.regioncusco.gob.simecr.security.domain.services.PersonaService;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ControlVerificacionServiceImpl implements ControlVerificacionService {
    private static final Logger LOG = LoggerFactory.getLogger(ControlVerificacionServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private MedidasControlService medidasControlService;
    @Autowired private ControlVerificacionPersistence controlVerificacionPersistence;
    @Autowired private ControlVerificacionMapper controlVerificacionMapper;
    @Autowired private PersonaService personaService;

    @Override
    public ControlVerificacionDto save(ControlVerificacionBodyDto controlVerificacionBodyDto) {
        MedidasControl medidasControl = medidasControlService.findMedidaById(controlVerificacionBodyDto.getMedidasControl());
        ControlVerificacion controlVerificacion = new ControlVerificacion();
        BeanUtils.copyProperties(controlVerificacionBodyDto, controlVerificacion);
        controlVerificacion.setMedidasControl(medidasControl);

        ControlVerificacion nuevoVeficacion = controlVerificacionPersistence.save(controlVerificacion, accessToken.getUserId(), ControlVerificacionEstado.PENDIENTE.value());
        LOG.info("Nuevo Medio de Verificacion para el control {}, creado", nuevoVeficacion.getId());
        return controlVerificacionMapper.toControlVerificacionDto(nuevoVeficacion);
    }

    @Override
    public List<ControlVerificacionDto> findAll() {
        return null;
    }

    @Override
    public List<ControlVerificacion> findAllControlVerificacion() {
        return controlVerificacionPersistence.findAll();
    }

    @Override
    public List<ControlVerificacionDto> findAllByMedidaControl(Long medidadControl) {
        MedidasControl medidasControl = medidasControlService.findMedidaById(medidadControl);
        List<ControlVerificacion> controlVerificacions = controlVerificacionPersistence.findAllByMedidaControl(medidasControl);
        return controlVerificacions.stream().map(controlVerificacion -> controlVerificacionMapper.toControlVerificacionDto(controlVerificacion)).collect(Collectors.toList());
    }

    @Override
    public List<Oficina> setResponsableOficina(Long controlVerificacion, List<Oficina> oficinas) {
        ControlVerificacion controlVerif = findById(controlVerificacion);
        controlVerif.setOficinas(oficinas);
        ControlVerificacion update = controlVerificacionPersistence.updateOficina(controlVerif);
        return update.getOficinas();
    }

    @Override
    public ControlVerificacion findControlVerificacionById(Long id) {
        return findById(id);
    }

    @Override
    public ControlVerificacionDto find(Long id) {
        ControlVerificacion controlVerificacion = findById(id);
        return controlVerificacionMapper.toControlVerificacionDto(controlVerificacion);
    }

    @Override
    public List<ControlVerificacionDto> findAllByUsuario() {
        Persona persona = personaService.findById(accessToken.getUserId());
        if(persona.getPerfil().getDescripcion().equals("ADMINISTRADOR")){
            List<ControlVerificacion> controlVerificacionList = controlVerificacionPersistence.findAll();
            return controlVerificacionList.stream().map(controlVerificacion -> controlVerificacionMapper.toControlVerificacionDto(controlVerificacion)).collect(Collectors.toList());
        }
        List<Oficina> oficinas = new ArrayList<>();
        oficinas.add(persona.getArea());
        List<ControlVerificacion> controlVerificacions = controlVerificacionPersistence.findAllByOficinas(oficinas);
        return controlVerificacions.stream().map(controlVerificacion -> controlVerificacionMapper.toControlVerificacionDto(controlVerificacion)).collect(Collectors.toList());
    }

    private ControlVerificacion findById(Long id){
        Optional<ControlVerificacion> controlVerificacion = controlVerificacionPersistence.findById(id);
        if(!controlVerificacion.isPresent()){
            LOG.error("No existe medio de verificacion con id {}", id);
            throw new NotFoundException("No existe Medio de Vericación");
        }
        return controlVerificacion.get();
    }
}
