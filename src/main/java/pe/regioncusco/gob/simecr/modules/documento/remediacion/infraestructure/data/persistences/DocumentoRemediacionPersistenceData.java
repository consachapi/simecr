package pe.regioncusco.gob.simecr.modules.documento.remediacion.infraestructure.data.persistences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.models.DocumentoRemediacion;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.domain.persistences.DocumentoRemediacionPersistence;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.infraestructure.data.entities.DocumentoRemediacionEntity;
import pe.regioncusco.gob.simecr.modules.documento.remediacion.infraestructure.data.repositorys.DocumentoRemediacionEntityRepository;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.ActividadRemediacion;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.commons.ActividadRemediacionAdapterCommon;
import pe.regioncusco.gob.simecr.modules.remediacion.infraestructure.data.entities.ActividadRemediacionEntity;
import pe.regioncusco.gob.simecr.security.data.entities.PersonaEntity;
import pe.regioncusco.gob.simecr.security.domain.commons.PersonaCommon;
import pe.regioncusco.gob.simecr.security.domain.models.Persona;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DocumentoRemediacionPersistenceData implements DocumentoRemediacionPersistence {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentoRemediacionPersistenceData.class);
    @Autowired private DocumentoRemediacionEntityRepository documentoRemediacionEntityRepository;

    @Override
    public List<DocumentoRemediacion> findAll() {
        List<DocumentoRemediacionEntity> documentoRemediacionEntities = documentoRemediacionEntityRepository.findAll();
        LOG.info("Documentos entidad de tipo remediacion son en total {}", documentoRemediacionEntities.size());
        return documentoRemediacionEntities.stream().map(documentoRemediacionEntity -> documentoRemediacionEntity.toDocumentoRemediacion()).collect(Collectors.toList());
    }

    @Override
    public List<DocumentoRemediacion> findAllByActividad(ActividadRemediacion actividadRemediacion) {
        ActividadRemediacionEntity actividadRemediacionEntity = ActividadRemediacionAdapterCommon.toActividadRemediacionEntity(actividadRemediacion);
        List<DocumentoRemediacionEntity> documentoRemediacionEntities = documentoRemediacionEntityRepository.findAllByActividadRemediacion(actividadRemediacionEntity);
        LOG.info("Documentos entidad de tipo remediacion encontrado para {}, son {}", actividadRemediacion.getId(), documentoRemediacionEntities.size());
        return documentoRemediacionEntities.stream().map(documentoRemediacionEntity -> documentoRemediacionEntity.toDocumentoRemediacion()).collect(Collectors.toList());
    }

    @Override
    public DocumentoRemediacion create(DocumentoRemediacion documentoRemediacion, Persona usuario) {
        PersonaEntity persona = PersonaCommon.toPersonaEntity(usuario);

        DocumentoRemediacionEntity documentoRemediacionEntity = new DocumentoRemediacionEntity();
        documentoRemediacionEntity.setActividadRemediacion(ActividadRemediacionAdapterCommon.toActividadRemediacionEntity(documentoRemediacion.getActividadRemediacion()));
        documentoRemediacionEntity.setNombreAchivo(documentoRemediacion.getNombreAchivo());
        documentoRemediacionEntity.setArchivo(documentoRemediacion.getArchivo());
        documentoRemediacionEntity.setFechaRegistro(new Date());
        documentoRemediacionEntity.setResponsable(persona);
        documentoRemediacionEntity.setEstado(documentoRemediacion.getEstado());

        DocumentoRemediacionEntity documentoRemediacionEntityUpdate = documentoRemediacionEntityRepository.save(documentoRemediacionEntity);
        LOG.info("Documento entidad remediacion creado con id {}", documentoRemediacionEntityUpdate.getId());
        return documentoRemediacionEntityUpdate.toDocumentoRemediacion();
    }

    @Override
    public DocumentoRemediacion update(DocumentoRemediacion documentoRemediacion, Integer estado, String usuario) {
        DocumentoRemediacionEntity documentoRemediacionEntity = documentoRemediacionEntityRepository.findById(documentoRemediacion.getId()).get();
        documentoRemediacionEntity.setNombreAchivo(documentoRemediacion.getNombreAchivo());
        documentoRemediacionEntity.setActividadRemediacion(ActividadRemediacionAdapterCommon.toActividadRemediacionEntity(documentoRemediacion.getActividadRemediacion()));
        documentoRemediacionEntity.setArchivo(documentoRemediacion.getArchivo());
        documentoRemediacionEntity.setEstado(estado);

        DocumentoRemediacionEntity documentoRemediacionEntityUpdate = documentoRemediacionEntityRepository.save(documentoRemediacionEntity);
        LOG.info("Documento entidad remediacion actualizado con Id {}", documentoRemediacionEntityUpdate.getId());
        return documentoRemediacionEntityUpdate.toDocumentoRemediacion();
    }

    @Override
    public Optional<DocumentoRemediacion> findById(Long id) {
        Optional<DocumentoRemediacionEntity> optionalDocumentoRemediacionEntity = documentoRemediacionEntityRepository.findById(id);
        return optionalDocumentoRemediacionEntity.map(documentoRemediacionEntity -> documentoRemediacionEntity.toDocumentoRemediacion());
    }

    @Override
    public void delete(DocumentoRemediacion documentoRemediacion) {
        Optional<DocumentoRemediacionEntity> optionalDocumentoRemediacionEntity = documentoRemediacionEntityRepository.findById(documentoRemediacion.getId());
        LOG.info("Documento Remediacion Entity {}, eliminado", optionalDocumentoRemediacionEntity.get());
        documentoRemediacionEntityRepository.delete(optionalDocumentoRemediacionEntity.get());
    }

}
