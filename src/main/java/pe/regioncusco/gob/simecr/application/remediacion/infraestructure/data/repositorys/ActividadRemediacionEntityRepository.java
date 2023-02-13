package pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities.ActividadRemediacionEntity;
import pe.regioncusco.gob.simecr.application.remediacion.infraestructure.data.entities.RemediacionVerificacionEntity;

import java.util.List;

@Repository
public interface ActividadRemediacionEntityRepository extends JpaRepository<ActividadRemediacionEntity, Long> {
    List<ActividadRemediacionEntity> findAllByRemediacionVerificacionOrderById(RemediacionVerificacionEntity remediacionVerificacionEntity);
}
