package pe.regioncusco.gob.simecr.modules.documento.remediacion.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.regioncusco.gob.simecr.commons.ParamsManager;
import pe.regioncusco.gob.simecr.config.Rest;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.models.dtos.DocumentoRemediacionDto;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.services.DocumentoRemediacionService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(DocumentoRemediacionController.DOCUMENTO_REMEDIACIOM)
public class DocumentoRemediacionController {
    public static final String DOCUMENTO_REMEDIACIOM = "/v1/documento/remediacion";
    private static final String LISTAR_POR_ACTIVIDAD = "/listar/actividad/{id}";
    private static final String UPLOAD = "/upload/{id}";
    private static final String VER_DOCUMENTO = "/mostrar/{archivo}";
    private static final String ELIMINAR = "/eliminar/{id}";

    @Autowired
    private DocumentoRemediacionService documentoRemediacionService;

    @GetMapping(LISTAR_POR_ACTIVIDAD)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<List<DocumentoRemediacionDto>> findAllByActividadControl(@PathVariable Long id){
        return new ResponseEntity<>(documentoRemediacionService.findAllByActividadRemediacion(id), HttpStatus.OK);
    }

    @PostMapping(UPLOAD)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<DocumentoRemediacionDto> upload(@PathVariable Long id, @RequestParam("file") MultipartFile file){
        return new ResponseEntity<>(documentoRemediacionService.uploadFile(id, file), HttpStatus.CREATED);
    }

    @GetMapping(VER_DOCUMENTO)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<String> findFile(@PathVariable String archivo){
        return new ResponseEntity<>(documentoRemediacionService.findFile(archivo), HttpStatus.OK);
    }

    @DeleteMapping(ELIMINAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public void delete(@PathVariable Long id){
        documentoRemediacionService.delete(id);
    }
}
