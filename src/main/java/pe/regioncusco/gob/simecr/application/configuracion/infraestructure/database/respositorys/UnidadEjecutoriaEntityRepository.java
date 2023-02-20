package pe.regioncusco.gob.simecr.application.configuracion.infraestructure.database.respositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.configuracion.infraestructure.database.entities.UnidadEjecutoriaEntity;

import java.util.List;

@Repository
public interface UnidadEjecutoriaEntityRepository extends JpaRepository<UnidadEjecutoriaEntity, String> {
    List<UnidadEjecutoriaEntity> findAllByEnabled(boolean enabled);

}
