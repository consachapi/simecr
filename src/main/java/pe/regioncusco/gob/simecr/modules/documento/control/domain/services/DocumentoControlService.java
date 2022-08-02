package pe.regioncusco.gob.simecr.modules.documento.control.domain.services;

import org.springframework.web.multipart.MultipartFile;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.models.dtos.DocumentoControlDto;

import java.util.List;

public interface DocumentoControlService {
    List<DocumentoControlDto> findAll();
    List<DocumentoControlDto> findAllByActividadControl(Long id);
    DocumentoControlDto uploadFile(Long id, MultipartFile file);
    String findFile(String archivo);
    void delete(Long id);
}
