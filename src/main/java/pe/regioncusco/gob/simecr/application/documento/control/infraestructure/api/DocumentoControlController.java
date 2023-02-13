package pe.regioncusco.gob.simecr.application.documento.control.infraestructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.config.Rest;
import pe.regioncusco.gob.simecr.application.documento.control.domain.models.dtos.DocumentoControlDto;
import pe.regioncusco.gob.simecr.application.documento.control.domain.services.DocumentoControlService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Rest
@RequestMapping(DocumentoControlController.DOCUMENTO_CONTROL)
public class DocumentoControlController {
    public static final String DOCUMENTO_CONTROL = "/v1/documento/control";
    private static final String LISTAR_POR_ACTIVIDAD = "/listar/actividad/{id}";
    private static final String UPLOAD = "/upload/{id}";
    private static final String VER_DOCUMENTO = "/mostrar/{archivo}";
    private static final String ELIMINAR = "/eliminar/{id}";

    @Autowired private DocumentoControlService documentoControlService;

    @GetMapping(LISTAR_POR_ACTIVIDAD)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<List<DocumentoControlDto>> findAllByActividadControl(@PathVariable Long id){
        return new ResponseEntity<>(documentoControlService.findAllByActividadControl(id), HttpStatus.OK);
    }

    @PostMapping(UPLOAD)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<DocumentoControlDto> upload(@PathVariable Long id, @RequestParam("file")MultipartFile file){
        return new ResponseEntity<>(documentoControlService.uploadFile(id, file), HttpStatus.CREATED);
    }

    @GetMapping(VER_DOCUMENTO)
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public ResponseEntity<String> findFile(@PathVariable String archivo){
        return new ResponseEntity<>(documentoControlService.findFile(archivo), HttpStatus.OK);
    }

    @DeleteMapping(ELIMINAR)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RolesAllowed(ParamsManager.ROLE_USER)
    public void delete(@PathVariable Long id){
        documentoControlService.delete(id);
    }

}
