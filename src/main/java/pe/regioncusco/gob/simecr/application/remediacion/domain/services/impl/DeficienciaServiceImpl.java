package pe.regioncusco.gob.simecr.application.remediacion.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.application.remediacion.domain.persistences.DeficienciaPersistence;
import pe.regioncusco.gob.simecr.core.common.MyValue;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.parametros.domain.models.Componente;
import pe.regioncusco.gob.simecr.application.parametros.domain.services.ComponenteService;
import pe.regioncusco.gob.simecr.application.remediacion.domain.enums.DeficienciaEstado;
import pe.regioncusco.gob.simecr.application.remediacion.domain.mappers.DeficienciaMapper;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.Deficiencia;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.DeficienciaBodyDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.dtos.DeficienciaDto;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.DeficienciaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeficienciaServiceImpl implements DeficienciaService {
    private static final Logger LOG = LoggerFactory.getLogger(DeficienciaServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private DeficienciaPersistence deficienciaPersistence;
    @Autowired private ComponenteService componenteService;
    @Autowired private DeficienciaMapper deficienciaMapper;

    @Override
    public DeficienciaDto save(DeficienciaBodyDto deficienciaBodyDto) {
        Componente componente = componenteService.findComponenteById(deficienciaBodyDto.getComponente());
        Deficiencia deficiencia = new Deficiencia();
        deficiencia.setDeficiencia(deficienciaBodyDto.getDeficiencia());
        deficiencia.setComponente(componente);
        deficiencia.setObservacion(deficienciaBodyDto.getObservacion());
        deficiencia.setPeriodo(2022);

        Deficiencia nuevo = deficienciaPersistence.save(deficiencia, accessToken.getUserId(), DeficienciaEstado.ACTIVO.value());
        LOG.info("Nueva deficiencia SCI {} creado", nuevo.getId());
        return deficienciaMapper.toDeficienciaDto(nuevo);
    }

    @Override
    public List<DeficienciaDto> findAll() {
        List<Deficiencia> deficiencias = deficienciaPersistence.findAll();
        return deficiencias.stream().map(deficiencia -> deficienciaMapper.toDeficienciaDto(deficiencia)).collect(Collectors.toList());
    }

    @Override
    public DeficienciaDto show(Long id) {
        Deficiencia deficiencia = findById(id);
        return deficienciaMapper.toDeficienciaDto(deficiencia);
    }

    @Override
    public DeficienciaDto update(Long id, DeficienciaBodyDto deficienciaBodyDto) {
        Deficiencia deficiencia = findById(id);
        Componente componente = componenteService.findComponenteById(deficienciaBodyDto.getComponente());
        deficiencia.setComponente(componente);
        deficiencia.setDeficiencia(deficienciaBodyDto.getDeficiencia());
        deficiencia.setObservacion(deficienciaBodyDto.getObservacion());
        deficiencia.setEstado(deficienciaBodyDto.getEstado());

        Deficiencia update = deficienciaPersistence.save(deficiencia, accessToken.getUserId(), deficienciaBodyDto.getEstado());
        LOG.info("Deficiencia SCI {} actualizado", update.getId());
        return deficienciaMapper.toDeficienciaDto(update);
    }

    @Override
    public void delete(Long id) {
        Deficiencia deficiencia = findById(id);
        deficiencia.setEstado(DeficienciaEstado.ANULADO.value());
        deficienciaPersistence.save(deficiencia, accessToken.getUserId(), DeficienciaEstado.ANULADO.value());
    }

    @Override
    public List<MyValue> selection() {
        List<Deficiencia> deficiencias = deficienciaPersistence.findAllByEstado(DeficienciaEstado.ACTIVO.value());
        return deficiencias.stream().map(deficiencia -> deficienciaMapper.toMyValue(deficiencia)).collect(Collectors.toList());
    }

    @Override
    public Deficiencia findDeficienciaById(Long id) {
        return findById(id);
    }

    private Deficiencia findById(Long id){
        Optional<Deficiencia> optionalDeficiencia = deficienciaPersistence.findById(id);
        if(!optionalDeficiencia.isPresent()){
            LOG.error("No existe deficiencia con id {}", id);
            throw new NotFoundException("No existe Deficiencia");
        }
        return optionalDeficiencia.get();
    }

}
