package pe.regioncusco.gob.simecr.application.control.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.ProductoEntity;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.RiesgoEntity;

import java.util.List;

@Repository
public interface RiesgoEntityRepository extends JpaRepository<RiesgoEntity, Long> {
    List<RiesgoEntity> findAllByProducto(ProductoEntity productoEntity);
}
