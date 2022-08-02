package pe.regioncusco.gob.simecr.modules.documento.control.domain.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.regioncusco.gob.simecr.commons.ParamsManager;
import pe.regioncusco.gob.simecr.commons.Utils;
import pe.regioncusco.gob.simecr.config.AccessTokenImpl;
import pe.regioncusco.gob.simecr.exceptions.BadRequestException;
import pe.regioncusco.gob.simecr.exceptions.NotFoundException;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.modules.control.domain.services.ActividadControlService;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.enums.DocumentoEstado;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.mappers.DocumentoControlMapper;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.models.DocumentoControl;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.models.dtos.DocumentoControlDto;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.persistences.DocumentoControlPersistence;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.services.DocumentoControlService;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;
import pe.regioncusco.gob.simecr.security.domain.services.PersonaService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentoControlServiceImpl implements DocumentoControlService {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentoControlServiceImpl.class);

    @Autowired private AccessTokenImpl accessToken;
    @Autowired private DocumentoControlPersistence documentoControlPersistence;
    @Autowired private DocumentoControlMapper documentoControlMapper;
    @Autowired private ActividadControlService actividadControlService;
    @Autowired private PersonaService personaService;

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
        try {
            String fileName = Utils.generateFileName();
            LOG.info("Nombre del archivo {}", file.getOriginalFilename());
            Files.copy(file.getInputStream(), ParamsManager.PATH_DOCUMENTOS_CONTROL.resolve(fileName + ".pdf"));
            LOG.info("Archivo copiado con nombre {}",fileName);

            DocumentoControl documentoControl = new DocumentoControl();
            documentoControl.setActividadControl(actividadControl);
            documentoControl.setNombreAchivo(file.getOriginalFilename());
            documentoControl.setArchivo(fileName);
            documentoControl.setEstado(DocumentoEstado.CARGADO.value());

            DocumentoControl nuevo = documentoControlPersistence.create(documentoControl, persona);
            LOG.info("Documento de control guardado con id {}", nuevo.getId());
            return documentoControlMapper.toDocumentoControlDto(nuevo);
        } catch (IOException e) {
            LOG.error("Error al guardar el archivo {}, exception {}", file.getName(), e.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al guardar el archivo " + file.getName());
        }
    }

    @Override
    public String findFile(String archivo) {
        String pathFile = "control" + File.separator + archivo + ".pdf";
        return Utils.encodeFile(pathFile);
    }

    @Override
    public void delete(Long id) {
        DocumentoControl documentoControl = findById(id);
        documentoControlPersistence.delete(documentoControl);
    }

    private DocumentoControl findById(Long id){
        Optional<DocumentoControl> optionalDocumentoControl = documentoControlPersistence.findById(id);
        if(!optionalDocumentoControl.isPresent()){
            LOG.error("No existe el documento con ID {}", id);
            throw new NotFoundException("No existe el registre del documento.");
        }
        return optionalDocumentoControl.get();
    }
}
