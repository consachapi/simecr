package pe.regioncusco.gob.simecr.modules.documento.control.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.control.domain.models.ActividadControl;
import pe.regioncusco.gob.simecr.modules.control.infraestructure.data.commons.ActividadControlAdapterCommon;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.models.DocumentoControl;
import pe.regioncusco.gob.simecr.modules.documento.control.domain.persistences.DocumentoControlPersistence;
import pe.regioncusco.gob.simecr.modules.documento.control.infraestructure.data.entities.DocumentoControlEntity;
import pe.regioncusco.gob.simecr.modules.documento.control.infraestructure.data.repositorys.DocumentoControlEntityRepository;
import pe.regioncusco.gob.simecr.security.data.entities.PersonaEntity;
import pe.regioncusco.gob.simecr.security.domain.commons.PersonaCommon;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DocumentoControlPersistenceData implements DocumentoControlPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentoControlPersistenceData.class);
    @Autowired private DocumentoControlEntityRepository documentoControlEntityRepository;

    @Override
    public List<DocumentoControl> findAll() {
        List<DocumentoControlEntity> documentoControlEntities = documentoControlEntityRepository.findAll();
        LOG.info("Documentos entidad de tipo control son en total {}", documentoControlEntities.size());
        return documentoControlEntities.stream().map(documentoControlEntity -> documentoControlEntity.toDocumentoControl()).collect(Collectors.toList());
    }

    @Override
    public List<DocumentoControl> findAllByActividad(ActividadControl actividadControl) {
        List<DocumentoControlEntity> documentoControlEntities = documentoControlEntityRepository.findAllByActividadControl(ActividadControlAdapterCommon.toActividadControlEntity(actividadControl));
        LOG.info("Documentos entidad de tipo control encontrado para {}, son {}", actividadControl.getId(), documentoControlEntities.size());
        return documentoControlEntities.stream().map(documentoControlEntity -> documentoControlEntity.toDocumentoControl()).collect(Collectors.toList());
    }

    @Override
    public DocumentoControl create(DocumentoControl documentoControl, Persona usuario) {
        PersonaEntity persona = PersonaCommon.toPersonaEntity(usuario);
        DocumentoControlEntity documentoControlEntity = new DocumentoControlEntity();
        documentoControlEntity.setActividadControl(ActividadControlAdapterCommon.toActividadControlEntity(documentoControl.getActividadControl()));
        documentoControlEntity.setNombreAchivo(documentoControl.getNombreAchivo());
        documentoControlEntity.setArchivo(documentoControl.getArchivo());
        documentoControlEntity.setFechaRegistro(new Date());
        documentoControlEntity.setResponsable(persona);
        documentoControlEntity.setEstado(documentoControl.getEstado());
        DocumentoControlEntity documentoControlEntityUpdate = documentoControlEntityRepository.save(documentoControlEntity);
        LOG.info("Documento entidad creado con id {}", documentoControlEntityUpdate.getId());
        return documentoControlEntityUpdate.toDocumentoControl();
    }

    @Override
    public DocumentoControl update(DocumentoControl documentoControl, Integer estado, String usuario) {
        DocumentoControlEntity documentoControlEntity = documentoControlEntityRepository.findById(documentoControl.getId()).get();
        documentoControlEntity.setNombreAchivo(documentoControl.getNombreAchivo());
        documentoControlEntity.setActividadControl(ActividadControlAdapterCommon.toActividadControlEntity(documentoControl.getActividadControl()));
        documentoControlEntity.setArchivo(documentoControl.getArchivo());

        documentoControlEntity.setEstado(estado);
        DocumentoControlEntity documentoControlEntityUpdate = documentoControlEntityRepository.save(documentoControlEntity);
        LOG.info("Documento entidad actualizado con Id {}", documentoControlEntityUpdate.getId());
        return documentoControlEntityUpdate.toDocumentoControl();
    }

    @Override
    public Optional<DocumentoControl> findById(Long id) {
        Optional<DocumentoControlEntity> optionalDocumentoControlEntity = documentoControlEntityRepository.findById(id);
        return optionalDocumentoControlEntity.map(documentoControlEntity -> documentoControlEntity.toDocumentoControl());
    }

    @Override
    public void delete(DocumentoControl documentoControl) {
        Optional<DocumentoControlEntity> optionalDocumentoControlEntity = documentoControlEntityRepository.findById(documentoControl.getId());
        documentoControlEntityRepository.delete(optionalDocumentoControlEntity.get());
    }
}
