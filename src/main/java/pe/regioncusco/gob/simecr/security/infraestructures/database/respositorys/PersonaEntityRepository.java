package pe.regioncusco.gob.simecr.security.infraestructures.database.respositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PerfilEntity;
import pe.regioncusco.gob.simecr.security.infraestructures.database.entities.PersonaEntity;

import java.util.List;

public interface PersonaEntityRepository extends PagingAndSortingRepository<PersonaEntity, String> {
    Page<PersonaEntity> findByNdocumentoContains(String ndocumento, Pageable pageable);
    Page<PersonaEntity> findAllByNdocumentoContains(String ndocumento, Pageable pageable);

    List<PersonaEntity> findByPerfil(PerfilEntity perfil);
}
