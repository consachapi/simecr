package pe.regioncusco.gob.simecr.application.remediacion.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.application.remediacion.domain.persistences.MedidasRemediacionPersistencia;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.remediacion.domain.enums.MedidasRemediacionEstado;
import pe.regioncusco.gob.simecr.application.remediacion.domain.mappers.MedidasRemediacionMapper;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.Deficiencia;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.MedidasRemediacionDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.DeficienciaService;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.MedidasRemediacionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedidasRemediacionServiceImpl implements MedidasRemediacionService {
    private static final Logger LOG = LoggerFactory.getLogger(MedidasRemediacionServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private MedidasRemediacionMapper medidasRemediacionMapper;
    @Autowired private MedidasRemediacionPersistencia medidasRemediacionPersistencia;
    @Autowired private DeficienciaService deficienciaService;

    @Override
    public MedidasRemediacionDto save(MedidasRemediacion medidasRemediacionDto) {
        Deficiencia deficiencia = deficienciaService.findDeficienciaById(medidasRemediacionDto.getDeficiencia().getId());

        MedidasRemediacion medidasRemediacion = new MedidasRemediacion();
        medidasRemediacion.setRemediacion(medidasRemediacionDto.getRemediacion());
        medidasRemediacion.setEstado(medidasRemediacionDto.getEstado());
        medidasRemediacion.setDeficiencia(deficiencia);

        MedidasRemediacion nuevo = medidasRemediacionPersistencia.save(medidasRemediacion, accessToken.getUserId(), MedidasRemediacionEstado.ACTIVO.value());
        LOG.info("Medida de Remediacion creado con id {}", nuevo.getId());
        return medidasRemediacionMapper.toMedidasRemediacionDto(nuevo);
    }

    @Override
    public MedidasRemediacionDto update(Long id, MedidasRemediacion medidasRemediacion) {
        MedidasRemediacion medidasRem = findById(id);

        Deficiencia deficiencia = deficienciaService.findDeficienciaById(medidasRemediacion.getDeficiencia().getId());
        medidasRem.setRemediacion(medidasRemediacion.getRemediacion());
        medidasRem.setDeficiencia(deficiencia);
        medidasRem.setEstado(medidasRemediacion.getEstado());

        MedidasRemediacion update = medidasRemediacionPersistencia.update(medidasRem, medidasRemediacion.getEstado());
        return medidasRemediacionMapper.toMedidasRemediacionDto(update);
    }

    @Override
    public List<MedidasRemediacionDto> findAll() {
        List<MedidasRemediacion> medidasRemediacions = medidasRemediacionPersistencia.findAll();
        LOG.info("Medidas de remediacion encontrado {}", medidasRemediacions.size());
        return medidasRemediacions.stream().map(medidasRemediacion -> medidasRemediacionMapper.toMedidasRemediacionDto(medidasRemediacion)).collect(Collectors.toList());
    }

    @Override
    public MedidasRemediacionDto find(Long id) {
        MedidasRemediacion medidasRemediacion = findById(id);
        return medidasRemediacionMapper.toMedidasRemediacionDto(medidasRemediacion);
    }

    @Override
    public MedidasRemediacion findMedidasRemediacionById(Long id) {
        return findById(id);
    }

    private MedidasRemediacion findById(Long id){
        Optional<MedidasRemediacion> optionalMedidasRemediacion = medidasRemediacionPersistencia.findById(id);
        if(!optionalMedidasRemediacion.isPresent()){
            LOG.error("Medida de Remediacion {} no existe.", id);
            throw new NotFoundException("No existe medida de remediación");
        }
        return optionalMedidasRemediacion.get();
    }
}
