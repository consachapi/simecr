package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.OficinaEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.MedidasRemediacionEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.RemediacionVerificacionEntity;

import java.util.List;

@Repository
public interface RemediacionVerificacionEntityRepository extends JpaRepository<RemediacionVerificacionEntity, Long> {
    List<RemediacionVerificacionEntity> findAllByMedidasRemediacion(MedidasRemediacionEntity medidasRemediacion);
    List<RemediacionVerificacionEntity> findAllByOficinasIn(List<OficinaEntity> oficinaEntities);
}
