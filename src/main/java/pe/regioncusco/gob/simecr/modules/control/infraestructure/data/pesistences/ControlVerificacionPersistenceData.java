package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.pesistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.control.domain.models.MedidasControl;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.MedidasControlDto;
import pe.regioncusco.gob.simecr.modules.control.domain.presistences.ControlVerificacionPersistence;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.commons.MedidaControlAdapterCommon;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ControlVerificacionEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.MedidasControlEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.repositorys.ControlVerificacionEntityRepository;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.commons.OficinaCommon;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.OficinaEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ControlVerificacionPersistenceData implements ControlVerificacionPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(ControlVerificacionPersistenceData.class);

    @Autowired private ControlVerificacionEntityRepository controlVerificacionEntityRepository;

    @Override
    public ControlVerificacion save(ControlVerificacion controlVerificacion, String usuario, Integer estado) {
        LOG.info("Guardando una nuevo medio de verificación");
        ControlVerificacionEntity controlVerificacionEntity = new ControlVerificacionEntity();
        BeanUtils.copyProperties(controlVerificacion, controlVerificacionEntity);
        controlVerificacionEntity.setFechaRegistro(new Date());
        controlVerificacionEntity.setUsuario(usuario);
        controlVerificacionEntity.setEstado(estado);

        MedidasControlEntity controlEntity = MedidaControlAdapterCommon.toMedidasControlEntity(controlVerificacion.getMedidasControl());
        controlVerificacionEntity.setMedidasControl(controlEntity);
        ControlVerificacionEntity nuevoControl = controlVerificacionEntityRepository.save(controlVerificacionEntity);
        LOG.info("Nuevo medio de verificación {} creado", nuevoControl.getId());
        return nuevoControl.toControlVerificacion();
    }

    @Override
    public ControlVerificacion updateOficina(ControlVerificacion controlVerificacion) {
        List<Oficina> oficinas = controlVerificacion.getOficinas();
        List<OficinaEntity> oficinaEntities = oficinas.stream().map(oficina -> OficinaCommon.toOficinaEntity(oficina)).collect(Collectors.toList());

        ControlVerificacionEntity controlVerificacionEntity = controlVerificacionEntityRepository.findById(controlVerificacion.getId()).get();
        controlVerificacionEntity.setOficinas(oficinaEntities);

        ControlVerificacionEntity update = controlVerificacionEntityRepository.save(controlVerificacionEntity);
        LOG.info("Oficinas del {}, actualizados", controlVerificacion.getId());
        return update.toControlVerificacion();
    }

    @Override
    public List<ControlVerificacion> findAll() {
        List<ControlVerificacionEntity> controlVerificacionEntities = controlVerificacionEntityRepository.findAll();
        LOG.info("Medios de Verificación encontrados {}", controlVerificacionEntities.size());
        return controlVerificacionEntities.stream().map(controlVerificacionEntity -> controlVerificacionEntity.toControlVerificacion()).collect(Collectors.toList());
    }

    @Override
    public List<ControlVerificacion> findAllByMedidaControl(MedidasControl medidasControl) {
        MedidasControlEntity controlEntity = MedidaControlAdapterCommon.toMedidasControlEntity(medidasControl);
        List<ControlVerificacionEntity> controlVerificacionEntities = controlVerificacionEntityRepository.findAllByMedidasControl(controlEntity);
        return controlVerificacionEntities.stream().map(controlVerificacionEntity -> controlVerificacionEntity.toControlVerificacion()).collect(Collectors.toList());
    }

    @Override
    public Optional<ControlVerificacion> findById(Long id) {
        Optional<ControlVerificacionEntity> optionalControlVerificacionEntity = controlVerificacionEntityRepository.findById(id);
        return optionalControlVerificacionEntity.map(controlVerificacionEntity -> controlVerificacionEntity.toControlVerificacion());
    }

    @Override
    public List<ControlVerificacion> findAllByOficinas(List<Oficina> oficinas) {
        List<OficinaEntity> oficinaEntities = oficinas.stream().map(oficina -> OficinaCommon.toOficinaEntity(oficina)).collect(Collectors.toList());
        List<ControlVerificacionEntity> controlVerificacionsEntities = controlVerificacionEntityRepository.findAllByOficinasIn(oficinaEntities);
        return controlVerificacionsEntities.stream().map(controlVerificacionEntity -> controlVerificacionEntity.toControlVerificacion()).collect(Collectors.toList());
    }
}
