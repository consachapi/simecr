package pe.regioncusco.gob.simecr.application.documento.remediacion.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.documento.remediacion.infraestructure.data.entities.DocumentoRemediacionEntity;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.ActividadRemediacionEntity;

import java.util.List;

@Repository
public interface DocumentoRemediacionEntityRepository extends JpaRepository<DocumentoRemediacionEntity, Long> {
    List<DocumentoRemediacionEntity> findAllByActividadRemediacion(ActividadRemediacionEntity actividadRemediacionEntity);
}
