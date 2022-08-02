package pe.regioncusco.gob.simecr.modules.control.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.regioncusco.gob.simecr.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.modules.control.domain.enums.MedidasControlEstado;
import pe.regioncusco.gob.simecr.modules.control.domain.mappers.MedidasControlMapper;
import pe.regioncusco.gob.simecr.modules.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.Riesgo;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.MedidasControlDto;
import pe.regioncusco.gob.simecr.modules.control.domain.presistences.MedidasControlPersistence;
import pe.regioncusco.gob.simecr.modules.control.domain.services.MedidasControlService;
import pe.regioncusco.gob.simecr.modules.control.domain.services.RiesgoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedidasControlServiceImpl implements MedidasControlService {
    private static final Logger LOG = LoggerFactory.getLogger(MedidasControlServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private MedidasControlPersistence medidasControlPersistence;
    @Autowired private RiesgoService riesgoService;
    @Autowired private MedidasControlMapper medidasControlMapper;

    @Override
    public MedidasControlDto save(MedidasControl medidasControl) {
        Riesgo riesgo = riesgoService.findRiesgoById(medidasControl.getRiesgo().getId());
        medidasControl.setRiesgo(riesgo);
        LOG.info("Creando medidas de control");
        MedidasControl control = medidasControlPersistence.save(medidasControl, accessToken.getUserId(), MedidasControlEstado.ACTIVO.value());
        return medidasControlMapper.toMedidasControlDto(control);
    }

    @Override
    public MedidasControlDto update(Long id, MedidasControl medidasControl) {
        MedidasControl mcontrol = findById(id);
        BeanUtils.copyProperties(medidasControl, mcontrol);
        mcontrol.setId(id);
        MedidasControl control = medidasControlPersistence.save(mcontrol, accessToken.getUserId(), medidasControl.getEstado());
        return medidasControlMapper.toMedidasControlDto(control);
    }

    @Override
    public List<MedidasControlDto> findAll() {
        return medidasControlPersistence.findAll().stream().map(medidasControl -> medidasControlMapper.toMedidasControlDto(medidasControl)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        MedidasControl medidasControl = findById(id);
        medidasControlPersistence.delete(medidasControl, accessToken.getUserId(), MedidasControlEstado.ANULADO.value());
    }

    @Override
    public MedidasControlDto findMedidaControlById(Long id) {
        MedidasControl medidasControl =  findById(id);
        return medidasControlMapper.toMedidasControlDto(medidasControl);
    }

    @Override
    public MedidasControl findMedidaById(Long id) {
        return findById(id);
    }

    private MedidasControl findById(Long id){
        Optional<MedidasControl> optionalMedidasControl = medidasControlPersistence.findById(id);
        if(!optionalMedidasControl.isPresent()){
            LOG.error("La medida de control con id {}, no existe", id);
            throw new NotFoundException("No existe la medida de control");
        }
        return optionalMedidasControl.get();
    }
}
