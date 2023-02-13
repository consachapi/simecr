package pe.regioncusco.gob.simecr.application.documento.control.infraestructure.data.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.application.control.infraestructure.data.entities.ActividadControlEntity;
import pe.regioncusco.gob.simecr.application.documento.control.infraestructure.data.entities.DocumentoControlEntity;

import java.util.List;

@Repository
public interface DocumentoControlEntityRepository extends JpaRepository<DocumentoControlEntity, Long> {
    List<DocumentoControlEntity> findAllByActividadControl(ActividadControlEntity actividadControlEntity);
}
