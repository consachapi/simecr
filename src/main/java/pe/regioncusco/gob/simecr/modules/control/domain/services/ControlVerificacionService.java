package pe.regioncusco.gob.simecr.modules.control.domain.services;

import pe.regioncusco.gob.simecr.modules.control.domain.models.ControlVerificacion;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ControlVerificacionBodyDto;
import pe.regioncusco.gob.simecr.modules.control.domain.models.dtos.ControlVerificacionDto;
import pe.regioncusco.gob.simecr.modules.parametros.domain.models.Oficina;

import java.util.List;

public interface ControlVerificacionService {
    ControlVerificacionDto save(ControlVerificacionBodyDto controlVerificacionBodyDto);
    List<ControlVerificacionDto> findAll();
    List<ControlVerificacion> findAllControlVerificacion();
    List<ControlVerificacionDto> findAllByMedidaControl(Long medidadControl);
    List<Oficina> setResponsableOficina(Long controlVerificacion, List<Oficina> oficinas);
    ControlVerificacion findControlVerificacionById(Long id);
    ControlVerificacionDto find(Long id);
    List<ControlVerificacionDto> findAllByUsuario();
}
