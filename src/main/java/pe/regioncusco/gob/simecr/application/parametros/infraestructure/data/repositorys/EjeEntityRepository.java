package pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.parametros.infraestructure.data.entities.EjeEntity;

@Repository
public interface EjeEntityRepository extends JpaRepository<EjeEntity, Long> {

}
