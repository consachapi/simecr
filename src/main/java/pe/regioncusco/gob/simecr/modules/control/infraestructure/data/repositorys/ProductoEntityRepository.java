package pe.regioncusco.gob.simecr.modules.control.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.entities.ProductoEntity;

import java.util.List;

@Repository
public interface ProductoEntityRepository extends JpaRepository<ProductoEntity, Long> {
    List<ProductoEntity> findAllByEstado(Integer estado);
}
