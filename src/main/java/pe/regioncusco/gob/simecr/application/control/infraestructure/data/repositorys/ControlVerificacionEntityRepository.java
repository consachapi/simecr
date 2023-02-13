package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ControlVerificacionEntity;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.MedidasControlEntity;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.OficinaEntity;

import java.util.List;

@Repository
public interface ControlVerificacionEntityRepository extends JpaRepository<ControlVerificacionEntity, Long> {
    List<ControlVerificacionEntity> findAllByMedidasControl(MedidasControlEntity medidasControlEntity);
    List<ControlVerificacionEntity> findAllByOficinasIn(List<OficinaEntity> oficinaEntities);
}
