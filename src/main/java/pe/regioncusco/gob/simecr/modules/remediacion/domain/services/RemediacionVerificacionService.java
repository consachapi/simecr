package pe.regioncusco.gob.simecr.modules.remediacion.domain.services;

import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ControlVerificacionDto;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.RemediacionVerificacion;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.RemediacionVerificacionBodyDto;
import pe.regioncusco.gob.simecr.modules.remediacion.domain.models.dtos.RemediacionVerificacionDto;

import java.util.List;

public interface RemediacionVerificacionService {
    RemediacionVerificacionDto save(RemediacionVerificacionBodyDto remediacionVerificacionBodyDto);
    List<RemediacionVerificacionDto> findAll();
    List<RemediacionVerificacion> findAllRemediacionVerificacion();
    List<RemediacionVerificacionDto> findAllByMedidaRemediacion(Long medidadRemediacion);
    List<Oficina> setResponsableOficina(Long controlVerificacion, List<Oficina> oficinas);
    RemediacionVerificacion findRemediacionVerificacionById(Long id);
    RemediacionVerificacionDto find(Long id);
    List<RemediacionVerificacionDto> findAllByUsuario();
}
