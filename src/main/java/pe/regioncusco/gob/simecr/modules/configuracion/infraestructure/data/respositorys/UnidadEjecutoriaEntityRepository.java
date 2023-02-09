package pe.regioncusco.gob.simecr.modules.configuracion.infraestructure.data.respositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.configuracion.infraestructure.data.entities.UnidadEjecutoriaEntity;

import java.util.List;

@Repository
public interface UnidadEjecutoriaEntityRepository extends JpaRepository<UnidadEjecutoriaEntity, String> {
    List<UnidadEjecutoriaEntity> findAllByEnabled(boolean enabled);
}
