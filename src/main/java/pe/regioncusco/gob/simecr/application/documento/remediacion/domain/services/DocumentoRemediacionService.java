package pe.regioncusco.gob.simecr.application.documento.remediacion.domain.services;

import org.springframework.web.multipart.MultipartFile;
import pe.regioncusco.gob.simecr.application.documento.remediacion.domain.models.dtos.DocumentoRemediacionDto;

import java.util.List;

public interface DocumentoRemediacionService {
    List<DocumentoRemediacionDto> findAll();
    List<DocumentoRemediacionDto> findAllByActividadRemediacion(Long id);
    DocumentoRemediacionDto uploadFile(Long id, MultipartFile file);
    String findFile(String archivo);
    void delete(Long id);
}
