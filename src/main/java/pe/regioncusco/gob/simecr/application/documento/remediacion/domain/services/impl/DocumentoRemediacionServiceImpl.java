package pe.regioncusco.gob.simecr.application.documento.remediacion.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pe.regioncusco.gob.simecr.application.documento.remediacion.domain.persistences.DocumentoRemediacionPersistence;
import pe.regioncusco.gob.simecr.core.common.ParamsManager;
import pe.regioncusco.gob.simecr.core.common.Utils;
import pe.regioncusco.gob.simecr.security.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.core.exceptions.BadRequestException;
import pe.regioncusco.gob.simecr.core.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.application.documento.control.domain.enums.DocumentoEstado;
import pe.regioncusco.gob.simecr.application.documento.remediacion.domain.mappers.DocumentoRemediacionMapper;
import pe.regioncusco.gob.simecr.application.documento.remediacion.domain.models.DocumentoRemediacion;
import pe.regioncusco.gob.simecr.application.documento.remediacion.domain.models.dtos.DocumentoRemediacionDto;
import pe.regioncusco.gob.simecr.application.documento.remediacion.domain.services.DocumentoRemediacionService;
import pe.regioncusco.gob.simecr.application.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.application.remediacion.domain.services.ActividadRemediacionService;
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
public class DocumentoRemediacionServiceImpl implements DocumentoRemediacionService {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentoRemediacionServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private DocumentoRemediacionPersistence documentoRemediacionPersistence;
    @Autowired private DocumentoRemediacionMapper documentoRemediacionMapper;
    @Autowired private ActividadRemediacionService actividadRemediacionService;
    @Autowired private PersonaService personaService;
    @Autowired private S3Service s3Service;

    @Override
    public List<DocumentoRemediacionDto> findAll() {
        List<DocumentoRemediacion> documentoRemediacions = documentoRemediacionPersistence.findAll();
        LOG.info("Documento de Remediacion encontrados un total de {}", documentoRemediacions.size());
        return documentoRemediacions.stream().map(documentoRemediacion -> documentoRemediacionMapper.toDocumentoRemediacionDto(documentoRemediacion)).collect(Collectors.toList());
    }

    @Override
    public List<DocumentoRemediacionDto> findAllByActividadRemediacion(Long id) {
        ActividadRemediacion actividadRemediacion = actividadRemediacionService.findActividadRemediacionById(id);
        List<DocumentoRemediacion> documentoRemediacions = documentoRemediacionPersistence.findAllByActividad(actividadRemediacion);

        return documentoRemediacions.stream().map(documentoRemediacion -> documentoRemediacionMapper.toDocumentoRemediacionDto(documentoRemediacion)).collect(Collectors.toList());
    }

    @Override
    public DocumentoRemediacionDto uploadFile(Long id, MultipartFile file) {
        ActividadRemediacion actividadRemediacion = actividadRemediacionService.findActividadRemediacionById(id);
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

        DocumentoRemediacion documentoRemediacion = new DocumentoRemediacion();
        documentoRemediacion.setActividadRemediacion(actividadRemediacion);
        documentoRemediacion.setNombreAchivo(file.getOriginalFilename());
        documentoRemediacion.setArchivo(fileNameHash);
        documentoRemediacion.setEstado(DocumentoEstado.CARGADO.value());

        DocumentoRemediacion nuevo = documentoRemediacionPersistence.create(documentoRemediacion, persona);
        LOG.info("Documento de remediacion guardado con id {}", nuevo.getId());
        return documentoRemediacionMapper.toDocumentoRemediacionDto(nuevo);
    }

    @Override
    public String findFile(String archivo) {
        return getFileRemote(archivo);
    }

    @Override
    public void delete(Long id) {
        DocumentoRemediacion documentoRemediacion = findById(id);
        documentoRemediacionPersistence.delete(documentoRemediacion);
        LOG.info("Documento de Remediacion eliminado");
    }

    private DocumentoRemediacion findById(Long id){
        Optional<DocumentoRemediacion> optionalDocumentoRemediacion = documentoRemediacionPersistence.findById(id);
        if(!optionalDocumentoRemediacion.isPresent()){
            LOG.error("Documento de remediacion con ID {}, no existe", id);
            throw new NotFoundException("Registro del documento no existe");
        }
        return optionalDocumentoRemediacion.get();
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
        return s3Service.putObject(multipartFile, ParamsManager.FOLDER_REMEDIACION + key);
    }

    private String getFileLocal(String archivo) {
        String pathFile = "remediacion" + File.separator + archivo + ".pdf";
        return Utils.encodeFile(pathFile);
    }

    private String getFileRemote(String archivo) {
        String key = ParamsManager.FOLDER_REMEDIACION + archivo + ".pdf";
        Asset asset = s3Service.getObject(key);
        return Utils.encodeBytes(asset.getContent());
    }
}
