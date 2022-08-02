package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.commons.MedidaControlAdapterCommon;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ControlVerificacionEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.MedidasControlEntity;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.commons.OficinaCommon;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.OficinaEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.MedidasRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.RemediacionVerificacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.persistences.RemediacionVerificacionPersistence;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.commons.MedidasRemediacionAdapterCommon;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.MedidasRemediacionEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.RemediacionVerificacionEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.repositorys.RemediacionVerificacionEntityRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RemediacionVerificacionPersistenceData implements RemediacionVerificacionPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(RemediacionVerificacionPersistenceData.class);

    @Autowired private RemediacionVerificacionEntityRepository remediacionVerificacionEntityRepository;

    @Override
    public RemediacionVerificacion save(RemediacionVerificacion remediacionVerificacion, String usuario, Integer estado) {
        LOG.info("Guardando una nueva medio de verificación");
        RemediacionVerificacionEntity remediacionVerificacionEntity = new RemediacionVerificacionEntity();
        BeanUtils.copyProperties(remediacionVerificacion, remediacionVerificacionEntity);
        remediacionVerificacionEntity.setFechaRegistro(new Date());
        remediacionVerificacionEntity.setUsuario(usuario);
        remediacionVerificacionEntity.setEstado(estado);

        MedidasRemediacionEntity remediacionEntity = MedidasRemediacionAdapterCommon.toMedidasRemediacionEntity(remediacionVerificacion.getMedidasRemediacion());
        remediacionVerificacionEntity.setMedidasRemediacion(remediacionEntity);
        RemediacionVerificacionEntity nuevoRemediacion = remediacionVerificacionEntityRepository.save(remediacionVerificacionEntity);
        LOG.info("Nuevo medio de verificación {} creado", nuevoRemediacion.getId());
        return nuevoRemediacion.toRemediacionVerificacion();
    }

    @Override
    public RemediacionVerificacion updateOficina(RemediacionVerificacion remediacionVerificacion) {
        List<Oficina> oficinas = remediacionVerificacion.getOficinas();
        List<OficinaEntity> oficinaEntities = oficinas.stream().map(oficina -> OficinaCommon.toOficinaEntity(oficina)).collect(Collectors.toList());

        RemediacionVerificacionEntity remediacionVerificacionEntity = remediacionVerificacionEntityRepository.findById(remediacionVerificacion.getId()).get();
        remediacionVerificacionEntity.setOficinas(oficinaEntities);

        RemediacionVerificacionEntity update = remediacionVerificacionEntityRepository.save(remediacionVerificacionEntity);
        LOG.info("Oficinas del {}, actualizados", update.getId());
        return update.toRemediacionVerificacion();
    }

    @Override
    public List<RemediacionVerificacion> findAll() {
        List<RemediacionVerificacionEntity> remediacionVerificacionEntities = remediacionVerificacionEntityRepository.findAll();
        LOG.info("Medios de Verificación encontrados {}", remediacionVerificacionEntities.size());
        return remediacionVerificacionEntities.stream().map(remediacionVerificacionEntity -> remediacionVerificacionEntity.toRemediacionVerificacion()).collect(Collectors.toList());
    }

    @Override
    public List<RemediacionVerificacion> findAllByMedidasRemediacion(MedidasRemediacion medidasRemediacion) {
        MedidasRemediacionEntity medidasRemediacionEntity = MedidasRemediacionAdapterCommon.toMedidasRemediacionEntity(medidasRemediacion);
        List<RemediacionVerificacionEntity> remediacionVerificacionEntities = remediacionVerificacionEntityRepository.findAllByMedidasRemediacion(medidasRemediacionEntity);
        LOG.info("Medios de Verificación para la remediacion encontrados {}", remediacionVerificacionEntities.size());
        return remediacionVerificacionEntities.stream().map(remediacionVerificacionEntity -> remediacionVerificacionEntity.toRemediacionVerificacion()).collect(Collectors.toList());
    }

    @Override
    public Optional<RemediacionVerificacion> findById(Long id) {
        Optional<RemediacionVerificacionEntity> optionalRemediacionVerificacionEntity = remediacionVerificacionEntityRepository.findById(id);
        return optionalRemediacionVerificacionEntity.map(remediacionVerificacionEntity -> remediacionVerificacionEntity.toRemediacionVerificacion());
    }

    @Override
    public List<RemediacionVerificacion> findAllByOficinas(List<Oficina> oficinas) {
        List<OficinaEntity> oficinaEntities = oficinas.stream().map(oficina -> OficinaCommon.toOficinaEntity(oficina)).collect(Collectors.toList());
        List<RemediacionVerificacionEntity> remediacionVerificacionEntities = remediacionVerificacionEntityRepository.findAllByOficinasIn(oficinaEntities);
        return remediacionVerificacionEntities.stream().map(remediacionVerificacionEntity -> remediacionVerificacionEntity.toRemediacionVerificacion()).collect(Collectors.toList());
    }
}
