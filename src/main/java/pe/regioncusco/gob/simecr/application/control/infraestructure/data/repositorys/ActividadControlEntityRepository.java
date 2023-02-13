package pe.regioncusco.gob.simecr.application.control.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.ActividadControlEntity;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.ControlVerificacionEntity;

import java.util.List;

@Repository
public interface ActividadControlEntityRepository extends JpaRepository<ActividadControlEntity, Long> {
    List<ActividadControlEntity> findAllByControlVerificacionOrderByFecha(ControlVerificacionEntity controlVerificacionEntity);
}
