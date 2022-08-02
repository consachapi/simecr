package pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.ComponenteEntity;
import pe.regioncusco.gob.simecr.modules.parametros.infraestructure.data.entities.EjeEntity;

import java.util.List;

@Repository
public interface ComponenteEntityRepository extends JpaRepository<ComponenteEntity, Long> {
    List<ComponenteEntity> findAllByEje(EjeEntity ejeEntity);
}
