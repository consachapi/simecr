package pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.services.impl;

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
import pe.regioncusco.gob.simecr.modules.documento.control.domain.enums.DocumentoEstado;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.mappers.DocumentoRemediacionMapper;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.models.DocumentoRemediacion;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.models.dtos.DocumentoRemediacionDto;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.persistences.DocumentoRemediacionPersistence;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.services.DocumentoRemediacionService;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.services.ActividadRemediacionService;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;
import pe.regioncusco.gob.simecr.security.domain.services.PersonaService;

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
        try {
            String fileName = Utils.generateFileName();
            LOG.info("Nombre del archivo {}", file.getOriginalFilename());
            Files.copy(file.getInputStream(), ParamsManager.PATH_DOCUMENTOS_REMEMDIACION.resolve(fileName + ".pdf"));
            LOG.info("Archivo copiado con nombre {}",fileName);

            DocumentoRemediacion documentoRemediacion = new DocumentoRemediacion();
            documentoRemediacion.setActividadRemediacion(actividadRemediacion);
            documentoRemediacion.setNombreAchivo(file.getOriginalFilename());
            documentoRemediacion.setArchivo(fileName);
            documentoRemediacion.setEstado(DocumentoEstado.CARGADO.value());

            DocumentoRemediacion nuevo = documentoRemediacionPersistence.create(documentoRemediacion, persona);
            LOG.info("Documento de remediacion guardado con id {}", nuevo.getId());
            return documentoRemediacionMapper.toDocumentoRemediacionDto(nuevo);
        } catch (IOException e) {
            LOG.error("Error al guardar el archivo {}, exception {}", file.getName(), e.getLocalizedMessage());
            throw new BadRequestException("Ocurrio un error al guardar el archivo " + file.getName());
        }
    }

    @Override
    public String findFile(String archivo) {
        String pathFile = "remediacion" + File.separator + archivo + ".pdf";
        return Utils.encodeFile(pathFile);
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
}
