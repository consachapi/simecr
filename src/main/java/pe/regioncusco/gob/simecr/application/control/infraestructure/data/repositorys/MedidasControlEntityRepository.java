package pe.regioncusco.gob.simecr.application.control.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.MedidasControlEntity;

@Repository
public interface MedidasControlEntityRepository extends JpaRepository<MedidasControlEntity, Long> {
}
