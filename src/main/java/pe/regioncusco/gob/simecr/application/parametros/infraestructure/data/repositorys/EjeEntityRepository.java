package pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.EjeEntity;

import java.util.List;

@Repository
public interface EjeEntityRepository extends JpaRepository<EjeEntity, Long> {

}
