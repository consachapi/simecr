package pe.regioncusco.gob.simecr.security.infraestructures.database.respositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PerfilEntity;

public interface PerfilEntityRepository extends JpaRepository<PerfilEntity, Long> {
}
