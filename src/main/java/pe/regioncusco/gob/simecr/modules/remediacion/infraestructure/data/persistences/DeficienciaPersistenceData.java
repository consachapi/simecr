package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Componente;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.commons.ComponenteCommon;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.ComponenteEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.Deficiencia;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.persistences.DeficienciaPersistence;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.DeficienciaEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.repositorys.DeficienciaEntityRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DeficienciaPersistenceData implements DeficienciaPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(DeficienciaPersistenceData.class);

    @Autowired private DeficienciaEntityRepository deficienciaEntityRepository;

    @Override
    public Deficiencia save(Deficiencia deficiencia, String usuario, Integer estado) {
        ComponenteEntity componenteEntity = ComponenteCommon.toComponenteEntity(deficiencia.getComponente());

        DeficienciaEntity deficienciaEntity = new DeficienciaEntity();
        BeanUtils.copyProperties(deficiencia, deficienciaEntity);
        deficienciaEntity.setComponente(componenteEntity);
        deficienciaEntity.setUsuario(usuario);
        deficienciaEntity.setFechaRegistro(new Date());
        deficienciaEntity.setEstado(estado);
        DeficienciaEntity nuevo = deficienciaEntityRepository.save(deficienciaEntity);
        LOG.info("Nueva entity deficiencia creado {}", nuevo.getId());
        return nuevo.toDeficiencia();
    }

    @Override
    public List<Deficiencia> findAll() {
        List<DeficienciaEntity> deficienciaEntities = deficienciaEntityRepository.findAll();
        return deficienciaEntities.stream().map(deficienciaEntity -> deficienciaEntity.toDeficiencia()).collect(Collectors.toList());
    }

    @Override
    public List<Deficiencia> findAllByEstado(Integer estado) {
        List<DeficienciaEntity> deficienciaEntities = deficienciaEntityRepository.findAllByEstado(estado);
        return deficienciaEntities.stream().map(deficienciaEntity -> deficienciaEntity.toDeficiencia()).collect(Collectors.toList());
    }

    @Override
    public Optional<Deficiencia> findById(Long id) {
        Optional<DeficienciaEntity> optionalDeficienciaEntity = deficienciaEntityRepository.findById(id);
        return optionalDeficienciaEntity.map(deficienciaEntity -> deficienciaEntity.toDeficiencia());
    }

}
