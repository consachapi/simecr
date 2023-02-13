package pe.regioncusco.gob.simecr.application.control.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.ControlVerificacionEntity;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.MedidasControlEntity;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.OficinaEntity;

import java.util.List;

@Repository
public interface ControlVerificacionEntityRepository extends JpaRepository<ControlVerificacionEntity, Long> {
    List<ControlVerificacionEntity> findAllByMedidasControl(MedidasControlEntity medidasControlEntity);
    List<ControlVerificacionEntity> findAllByOficinasIn(List<OficinaEntity> oficinaEntities);
}
