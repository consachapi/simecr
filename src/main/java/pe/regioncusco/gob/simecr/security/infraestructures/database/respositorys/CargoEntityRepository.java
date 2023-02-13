package pe.regioncusco.gob.simecr.security.data.respositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.regioncusco.gob.simecr.security.data.entities.CargoEntity;

public interface CargoEntityRepository extends JpaRepository<CargoEntity, Long> {
}
