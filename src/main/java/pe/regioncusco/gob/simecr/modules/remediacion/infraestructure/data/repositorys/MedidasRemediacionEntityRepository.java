package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.MedidasRemediacionEntity;

@Repository
public interface MedidasRemediacionEntityRepository extends JpaRepository<MedidasRemediacionEntity, Long> {

}
