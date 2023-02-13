package pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.OficinaEntity;

import java.util.Optional;

@Repository
public interface OficinaEntityRepository extends JpaRepository<OficinaEntity, String> {
    Optional<OficinaEntity> findByAbreviatura(String abreviatura);
}
