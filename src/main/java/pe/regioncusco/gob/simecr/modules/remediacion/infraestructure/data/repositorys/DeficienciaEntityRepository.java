package pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.DeficienciaEntity;

import java.util.List;

@Repository
public interface DeficienciaEntityRepository extends JpaRepository<DeficienciaEntity, Long> {
    List<DeficienciaEntity> findAllByEstado(Integer estado);
}
