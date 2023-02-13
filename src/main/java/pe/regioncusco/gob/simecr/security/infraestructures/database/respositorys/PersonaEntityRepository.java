package pe.regioncusco.gob.simecr.security.data.respositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pe.regioncusco.gob.simecr.security.data.entities.PerfilEntity;
import pe.regioncusco.gob.simecr.security.data.entities.PersonaEntity;

import java.util.List;

public interface PersonaEntityRepository extends PagingAndSortingRepository<PersonaEntity, String> {
    Page<PersonaEntity> findByNdocumentoContains(String ndocumento, Pageable pageable);
    Page<PersonaEntity> findAllByNdocumentoContains(String ndocumento, Pageable pageable);

    List<PersonaEntity> findByPerfil(PerfilEntity perfil);
}
