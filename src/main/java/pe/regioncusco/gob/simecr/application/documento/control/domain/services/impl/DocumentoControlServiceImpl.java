package pe.regioncusco.gob.simecr.application.documento.control.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.common.Utils;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.core.exceptions.BadRequestException;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.application.control.domain.services.ActividadControlService;
import pe.regioncusco.gob.simecr.application.documento.control.domain.enums.DocumentoEstado;
import pe.regioncusco.gob.simecr.application.documento.control.domain.mappers.DocumentoControlMapper;
import pe.regioncusco.gob.simecr.application.documento.control.domain.models.DocumentoControl;
import pe.regioncusco.gob.simecr.application.documento.control.domain.models.dtos.DocumentoControlDto;
import pe.regioncusco.gob.simecr.application.documento.control.domain.persistences.DocumentoControlPersistence;
import pe.regioncusco.gob.simecr.application.documento.control.domain.services.DocumentoControlService;
import pe.regioncusco.gob.simecr.s3.S3Service;
import pe.regioncusco.gob.simecr.s3.model.Asset;
import pe.regioncusco.gob.simecr.security.domains.models.Persona;
import pe.regioncusco.gob.simecr.security.applications.services.PersonaService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentoControlServiceImpl implements DocumentoControlService {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentoControlServiceImpl.class);

    @Autowired
    private AccessTokenImpl accessToken;
    @Autowired
    private DocumentoControlPersistence documentoControlPersistence;
    @Autowired
    private DocumentoControlMapper documentoControlMapper;
    @Autowired
    private ActividadControlService actividadControlService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private S3Service s3Service;

    @Override
    public List<DocumentoControlDto> findAll() {
        List<DocumentoControl> documentoControls = documentoControlPersistence.findAll();
        LOG.info("Documento de Control encontrados un total de {}", documentoControls.size());
        return documentoControls.stream().map(documentoControl -> documentoControlMapper.toDocumentoControlDto(documentoControl)).collect(Collectors.toList());
    }

    @Override
    public List<DocumentoControlDto> findAllByActividadControl(Long id) {
        ActividadControl actividadControl = actividadControlService.findActividadControlById(id);
        List<DocumentoControl> documentoControls = documentoControlPersistence.findAllByActividad(actividadControl);
        return documentoControls.stream().map(documentoControl -> documentoControlMapper.toDocumentoControlDto(documentoControl)).collect(Collectors.toList());
    }

    @Override
    public DocumentoControlDto uploadFile(Long id, MultipartFile file) {
        ActividadControl actividadControl = actividadControlService.findActividadControlById(id);
        Persona persona = personaService.findById(accessToken.getUserId());

        String fileNameHash = Utils.generateFileName();
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = String.format("%s.%s", fileNameHash, extension);
        String fileNameOriginal = file.getOriginalFilename();

        boolean responseSave = saveRemote(file, key);
        if (!responseSave) {
            LOG.error("Resultado al guardar el archivo {}", responseSave);
            throw new BadRequestException("Error al guardar el archivo " + fileNameOriginal);
        }

        DocumentoControl documentoControl = new DocumentoControl();
        documentoControl.setActividadControl(actividadControl);
        documentoControl.setNombreAchivo(file.getOriginalFilename());
        documentoControl.setArchivo(fileNameHash);
        documentoControl.setEstado(DocumentoEstado.CARGADO.value());

        DocumentoControl nuevo = documentoControlPersistence.create(documentoControl, persona);
        LOG.info("Documento de control guardado con id {}", nuevo.getId());
        return documentoControlMapper.toDocumentoControlDto(nuevo);
    }

    @Override
    public String findFile(String archivo) {
        return getFileRemote(archivo);
    }

    @Override
    public void delete(Long id) {
        DocumentoControl documentoControl = findById(id);
        documentoControlPersistence.delete(documentoControl);
    }

    private DocumentoControl findById(Long id) {
        Optional<DocumentoControl> optionalDocumentoControl = documentoControlPersistence.findById(id);
        if (!optionalDocumentoControl.isPresent()) {
            LOG.error("No existe el documento con ID {}", id);
            throw new NotFoundException("No existe el registre del documento.");
        }
        return optionalDocumentoControl.get();
    }

    private boolean saveLocal(MultipartFile multipartFile, String key) {
        try {
            LOG.info("Nombre del archivo {}", multipartFile.getOriginalFilename());
            Files.copy(multipartFile.getInputStream(), ParamsManager.PATH_DOCUMENTOS_CONTROL.resolve(key));
            LOG.info("Archivo guardago en local con nombre {}", key);
            return true;
        } catch (IOException e) {
            LOG.error("Error al guardar el archivo localmente {}, exception {}", multipartFile.getName(), e.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al guardar el archivo " + multipartFile.getName());
        }
    }

    private boolean saveRemote(MultipartFile multipartFile, String key) {
        return s3Service.putObject(multipartFile, ParamsManager.FOLDER_CONTROL + key);
    }

    private String getFileLocal(String archivo) {
        String pathFile = "control" + File.separator + archivo + ".pdf";
        return Utils.encodeFile(pathFile);
    }

    private String getFileRemote(String archivo) {
        String key = ParamsManager.FOLDER_CONTROL + archivo + ".pdf";
        Asset asset = s3Service.getObject(key);
        return Utils.encodeBytes(asset.getContent());
    }
}